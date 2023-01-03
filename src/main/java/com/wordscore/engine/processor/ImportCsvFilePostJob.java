package com.wordscore.engine.processor;

import com.opencsv.bean.CsvToBeanBuilder;
import com.wordscore.engine.database.entity.BlacklistResult;
import com.wordscore.engine.database.entity.ProcessedWords;
import com.wordscore.engine.rest.dto.UpdateKeywordRequestDTO;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ImportCsvFilePostJob extends ServiceFactory implements Job {

    public ImportCsvFilePostJob() {
    }

    @Override
    public void execute(JobExecutionContext context) {

        File directoryPath = new File("/opt/csv/nov");
        // Create a new subfolder called "processed" into source directory
        try {
            Path path = Path.of(directoryPath.getAbsolutePath() + "/processed");
            if (!Files.exists(path) || !Files.isDirectory(path)) {
                Files.createDirectory(path);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FilenameFilter textFileFilter = (dir, name) -> {
            String lowercaseName = name.toLowerCase();
            if (lowercaseName.endsWith(".csv")) {
                return true;
            } else {
                return false;
            }
        };
        // List of all the csv files
        File filesList[] = directoryPath.listFiles(textFileFilter);
        System.out.println("List of the text files in the specified directory:");

//        Optional<File> csvFile = Arrays.stream(filesList).findFirst();
//        File file = csvFile.get();

        for(File file : filesList) {
//            System.out.println("File name: "+file.getName());
//            System.out.println("File path: "+file.getAbsolutePath());
//            System.out.println("Size :"+file.getTotalSpace());
//            System.out.println(" ");

            try {
                try (var br = new FileReader(file.getAbsolutePath(), StandardCharsets.UTF_16)){
                    List<CsvLine> beans = new CsvToBeanBuilder(br)
                            .withType(CsvLine.class)
                            .withSeparator('\t')
                            .withSkipLines(3)
                            .build()
                            .parse();

                for (CsvLine item : beans) {

                    System.out.println(String.format("Keyword: %s, avgMonthlySearches: %s, lowRange: %f, highRange: %f",
                            item.getKeyword(),
                            item.getAvgMonthlySearches(),
                            item.getLowRange(),
                            item.getHighRange()));

                    Optional<ProcessedWords> isFound = processedWordsService.findByKeyword(item.getKeyword());

                    if (isFound.isPresent()) {

                        // Sometimes low range ang high range can be empty. We need to skip this case and update only ScoreUs
                        if (item.getLowRange() != null && item.getHighRange() != null) {
                            UpdateKeywordRequestDTO obj = UpdateKeywordRequestDTO.builder()
                                    .keyword(item.getKeyword()).seoScoreUs(item.getAvgMonthlySearches())
                                    .keywordsCount(countWords(item.getKeyword()))
                                    .lowRange(item.getLowRange()).highRange(item.getHighRange()).build();
                            processedWordsService.updateBySeoScoreUs(obj);
                        } else // Update all values based on keyword
                        {
                            UpdateKeywordRequestDTO obj = UpdateKeywordRequestDTO.builder()
                                    .keyword(item.getKeyword()).seoScoreUs(item.getAvgMonthlySearches())
                                    .keywordsCount(countWords(item.getKeyword()))
                                    .lowRange(item.getLowRange()).highRange(item.getHighRange()).build();
                            processedWordsService.update(obj);
                        }
                    } else // Insert all values as new db row
                    {
                        ProcessedWords obj = ProcessedWords.builder()
                                .keyword(item.getKeyword())
                                .seoScoreUs(item.getAvgMonthlySearches())
                                .lowRange(item.getLowRange())
                                .highRange(item.getHighRange())
                                .keywordsCount(countWords(item.getKeyword()))
                                .createdAt(LocalDateTime.now())
                                .build();
                        processedWordsService.save(obj);
                    }

                    // Check for blacklisted keyword

                        String keyword = item.getKeyword();
                        System.out.println("Checking blacklisted keyword: " + keyword);
                        List<BlacklistResult> blacklistedKeyword = blacklistedWordsService.findBlacklistedKeyword(keyword);

                        if(blacklistedKeyword.size() > 0 )
                        {
                            String foundBlacklistedKeyword = blacklistedKeyword.get(0).getKeyword();

                            System.out.println("Found blacklisted word " + foundBlacklistedKeyword + " in keyword: " + keyword);

                            processedWordsService.updateTrademarkBlacklistedByKeyword(keyword, foundBlacklistedKeyword);
                        }

                    // end of check for blacklisted keyword
            }}

            } catch (Exception e){
                e.printStackTrace();
            }

            // Move here file into new subdirectory when file processing is finished
            Path copied = Paths.get(file.getParent() + "/processed");
            Path originalPath = file.toPath();
            try {
                // Use resolve method to keep the "processed" as folder
                Files.move(originalPath, copied.resolve(originalPath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println(String.format("\nProcessed file : %s, moving the file to subfolder /processed\n",
                    originalPath));
        }
    }

    private int countWords(String keyword)
    {
        String[] words = keyword.trim().split("\\s+");

        System.out.println("count is = " + words.length);
        return words.length;
    }



}
