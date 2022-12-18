package com.wordscore.engine.processor;

import com.wordscore.engine.database.entity.BlacklistedWords;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

public class BlacklistPostJob extends ServiceFactory implements Job {

    // SELECT pg_size_pretty(pg_database_size('wordscore_engine'));

    public BlacklistPostJob() {
    }

    @Override
    public void execute(JobExecutionContext context) {

//        try {
//            FileInputStream fstream = new FileInputStream("C:\\in_progress\\test.txt");
//            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
//
//            String strLine;
//
//            while ((strLine = br.readLine()) != null) {
//
//                String[] result = strLine.split("\t");
//                String[] newArr = Arrays.copyOf(result, result.length - 16);
//
//                String keyword = newArr[0];
//                String score = newArr[1];
//
//                Optional<ProcessedWords> isFound = processedWordsService.findByKeyword(keyword);
//
//                if(isFound.isPresent())
//                {
//                    if(score.equals("NA")){
//                        score = "0";
//                    }
//
//                    UpdateKeywordRequestDTO obj = UpdateKeywordRequestDTO.builder().keyword(keyword).seoScoreUs(BigDecimal.valueOf(Long.parseLong(score))).build();
//                    processedWordsService.updateBySeoScoreUs(obj);
//                }
//                else
//                {
//                    if(score.contains("NA")){
//                        continue;
//                    }
//                    ProcessedWords obj = ProcessedWords.builder()
//                            .keyword(keyword)
//                            .seoScoreUs(BigDecimal.valueOf(Long.parseLong(score)))
//                            .isComDomainAvailable(false)
//                            .createdAt(LocalDateTime.now())
//                            .build();
//                    processedWordsService.save(obj);
//                }
//
//                System.out.println("keyword insert " + keyword);
//
//
//
////                System.out.println(Arrays.toString(newArr));
//            }
//
//            System.out.println("\n\n\n\nCOMPLETED\n\n\n\n");
//
//            fstream.close();
//        } catch (Exception e){
//            e.printStackTrace();
//        }


//        try {
//            BufferedReader reader;
//            try {
//                reader = new BufferedReader(new FileReader(
//                        "C:\\in_progress\\job.csv"));
//                String line = reader.readLine();
//                while (line != null) {
//                    System.out.println(line);
//
////                    Thread.sleep(20);
//                    Optional<ProcessedWords> isFound = processedWordsService.findByKeyword(line);
//
//                    if(!isFound.isPresent()){
//                        ProcessedWords obj = ProcessedWords.builder()
//                                .keyword(line)
//                                .createdAt(LocalDateTime.now())
//                                .build();
//                        processedWordsService.save(obj);
//                    }
//
//                    // read next line
//                    line = reader.readLine();
//                }
//                reader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }


//        try {
//            String fileName = "C:\\in_progress\\job.csv";
//
//            List<CsvLine> beans = new CsvToBeanBuilder(new FileReader(fileName))
//                    .withType(CsvLine.class)
//                    .withSkipLines(1)
//                    .build()
//                    .parse();
//
//            for(CsvLine item: beans){
//
//                Optional<ProcessedWords> isFound = processedWordsService.findByKeyword(item.getKeyword());
//
//                if(isFound.isPresent())
//                {
//                    UpdateKeywordRequestDTO obj = UpdateKeywordRequestDTO.builder().keyword(item.getKeyword()).seoScoreUs(item.getCpc()).build();
//                    processedWordsService.updateBySeoScoreUs(obj);
//                }
//                else
//                {
//                    ProcessedWords obj = ProcessedWords.builder()
//                            .keyword(item.getKeyword())
//                            .seoScoreUs(item.getCpc())
//                            .createdAt(LocalDateTime.now())
//                            .build();
//                    processedWordsService.save(obj);
//                }
//
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//        }


        try {
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(
                        "C:\\in_progress\\blacklistedtest.txt"));
                String line = reader.readLine();
                while (line != null) {
                    System.out.println(line);

                    Optional<BlacklistedWords> isFound = blacklistedWordsService.findByKeyword(line);

                    if(!isFound.isPresent()){
                        BlacklistedWords obj = BlacklistedWords.builder()
                                .keyword(line)
                                .createdAt(LocalDateTime.now())
                                .build();
                        blacklistedWordsService.save(obj);
                    }

                    // read next line
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

}
