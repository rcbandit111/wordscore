package com.wordscore.engine.processor;

import com.wordscore.engine.database.entity.BlacklistedWords;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

public class ImportBlacklistPostJob extends ServiceFactory implements Job {

    // SELECT pg_size_pretty(pg_database_size('wordscore_engine'));

    public ImportBlacklistPostJob() {
    }

    @Override
    public void execute(JobExecutionContext context) {

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
