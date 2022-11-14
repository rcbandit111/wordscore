package com.wordscore.engine.processor;

import com.opencsv.bean.CsvToBeanBuilder;
import com.wordscore.engine.database.entity.ProcessedWords;
import com.wordscore.engine.rest.dto.UpdateKeywordRequestDTO;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ImportCsvFilePostJob extends ServiceFactory implements Job {

    public ImportCsvFilePostJob() {
    }

    @Override
    public void execute(JobExecutionContext context) {

        File directoryPath = new File("C:\\csv\\nov_3");
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
        for(File file : filesList) {
//            System.out.println("File name: "+file.getName());
//            System.out.println("File path: "+file.getAbsolutePath());
//            System.out.println("Size :"+file.getTotalSpace());
//            System.out.println(" ");

            try {
                List<CsvLine> beans = new CsvToBeanBuilder(new FileReader(file.getAbsolutePath(), StandardCharsets.UTF_16))
                        .withType(CsvLine.class)
                        .withSeparator('\t')
                        .withSkipLines(3)
                        .build()
                        .parse();

                for(CsvLine item: beans){

                    System.out.println(String.format("Keyword: %s, avgMonthlySearches: %s, lowRange: %f, highRange: %f",
                            item.getKeyword(),
                            item.getAvgMonthlySearches(),
                            item.getLowRange(),
                            item.getHighRange()));

                        Optional<ProcessedWords> isFound = processedWordsService.findByKeyword(item.getKeyword());

                        if(isFound.isPresent())
                        {
                            // Sometimes low range ang high range can be empty. We need to skip this case and update only ScoreUs
                            if(item.getLowRange() != null && item.getHighRange() != null)
                            {
                                UpdateKeywordRequestDTO obj = UpdateKeywordRequestDTO.builder()
                                        .keyword(item.getKeyword()).seoScoreUs(item.getAvgMonthlySearches())
                                        .lowRange(item.getLowRange()).highRange(item.getHighRange()).build();
                                processedWordsService.updateBySeoScoreUs(obj);
                            }
                            else // Update all values based on keyword
                            {
                                UpdateKeywordRequestDTO obj = UpdateKeywordRequestDTO.builder()
                                        .keyword(item.getKeyword()).seoScoreUs(item.getAvgMonthlySearches())
                                        .lowRange(item.getLowRange()).highRange(item.getHighRange()).build();
                                processedWordsService.update(obj);
                            }
                        }
                        else // Insert all values as new db row
                        {
                            ProcessedWords obj = ProcessedWords.builder()
                                    .keyword(item.getKeyword())
                                    .seoScoreUs(item.getAvgMonthlySearches())
                                    .lowRange(item.getLowRange())
                                    .highRange(item.getHighRange())
                                    .createdAt(LocalDateTime.now())
                                    .build();
                            processedWordsService.save(obj);
                        }
                }

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
