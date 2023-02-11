package com.wordscore.engine.processor;

import com.opencsv.bean.CsvToBeanBuilder;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

public class DataValidationCheckJob extends ServiceFactory implements Job {

    public DataValidationCheckJob() {
    }

    @Override
    public void execute(JobExecutionContext context) {

        // preparing
        File directory = Path.of("C:\\csv\\nov").toFile();
        // we want to process only csv files from directory
        FilenameFilter csvFilter = (dir, name) -> name.toLowerCase().endsWith(".csv");

        // specify what conditions should meet the value of every line
        // to be considered as a valid line
        List<Predicate<BigDecimal>> fileMatchConditions = List.of(
                ne(new BigDecimal("50")),
                ne(new BigDecimal("500")),
                ne(new BigDecimal("5000"))
        );
        // start directory processing
        try {
            processDirectory(directory, csvFilter, fileMatchConditions);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void processDirectory(File directory, FilenameFilter filter,
                                        List<Predicate<BigDecimal>> fileMatchConditions) throws IOException {

        Path processedFolderPath = createDirectory(Path.of(directory.getAbsolutePath() + "/processed"));
        Path invalidFilesFolderPath = createDirectory(Path.of(directory.getAbsolutePath() + "/invalid_files"));

        File[] files = directory.listFiles(filter);

        if (Objects.nonNull(files)) {
            for (File file : files) {
                if (isFileValid(file, fileMatchConditions)) {
                    // if file is valid, then move it to the processed directory
                    System.out.println("Moving valid file " + file.getName() + " to " + file.getAbsolutePath());
                    moveFile(file, processedFolderPath, StandardCopyOption.REPLACE_EXISTING);
                } else {
                    // if file is not, then move it to the invalid-files directory
                    System.out.println("Moving invalid file " + file.getName() + " to " + file.getAbsolutePath());
                    moveFile(file, invalidFilesFolderPath, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    }

    /**
     * primary file validation
     * parses the file
     */
    public static boolean isFileValid(File file, List<Predicate<BigDecimal>> matchConditions) throws IOException {
        try (Reader reader = Files.newBufferedReader(file.getAbsoluteFile().toPath(), StandardCharsets.UTF_16)) {
            List<CsvLine> lineBeans = new CsvToBeanBuilder<CsvLine>(reader)
                    .withType(CsvLine.class)
                    .withSeparator('\t')
                    .withSkipLines(3)
                    .build()
                    .parse();

            // unique violations found within this file
            Set<BigDecimal> violations = new HashSet<>();
            for (CsvLine line : lineBeans) {
                // skip if value in the line is null
                if (Objects.isNull(line.getAvgMonthlySearches())) {
                    continue;
                }
                if (!isLineValid(line, matchConditions)) {
                    violations.add(line.getAvgMonthlySearches());
                }
                // if we reached all the violations, then file is not valid
                if (violations.size() == matchConditions.size()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * if all predicates return true, then line is valid
     */
    public static boolean isLineValid(CsvLine line, List<Predicate<BigDecimal>> conditions) {
        return conditions.stream().allMatch(predicate -> predicate.test(line.getAvgMonthlySearches()));
    }

    /**
     * move the file to the directory specified
     */
    public static void moveFile(File file, Path moveTo, StandardCopyOption option) throws IOException {
        Files.move(file.toPath(), moveTo.resolve(file.getName()), option);
    }

    /**
     * factory method for Predicate that returns true if compareTo != 0
     */
    public static <T extends Comparable<T>> Predicate<T> ne(T target) {
        return (value) -> value.compareTo(target) != 0;
    }

    public static Path createDirectory(Path path) throws IOException {
        if (!Files.exists(path) || !Files.isDirectory(path)) {
            return Files.createDirectory(path);
        }
        return path;
    }
}
