package com.wordscore.engine.processor;

import com.wordscore.engine.database.entity.ProcessedWords;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.util.Optional;

public class WordsCountJob extends ServiceFactory implements Job {

    public WordsCountJob() {
    }

    @Override
    public void execute(JobExecutionContext context) {

        Optional<ProcessedWords> keywords = processedWordsService.findRandomKeywordWhereWordsCountIsEmpty();

        if(keywords.isPresent())
        {
            String keyword = keywords.get().getKeyword();

            String[] words = keyword.trim().split("\\s+");

            System.out.println("count is = " + words.length);

            processedWordsService.updateKeywordCount(words.length, keyword);
        }
    }
}
