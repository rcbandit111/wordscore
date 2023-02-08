package com.wordscore.engine.processor;

import com.wordscore.engine.database.entity.BlacklistResult;
import com.wordscore.engine.database.entity.BlacklistedWords;
import com.wordscore.engine.database.entity.ProcessedWords;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BlacklistWordCheckJob extends ServiceFactory implements Job {

    public BlacklistWordCheckJob() {
    }

    @Override
    public void execute(JobExecutionContext context) {

//        Optional<ProcessedWords> keywords = processedWordsService.findRandomKeywordWhereTrademarkBlacklistedIsEmpty();
//
//        if(keywords.isPresent())
//        {
//            String keyword = keywords.get().getKeyword();
//
//            System.out.println("Checking blacklisted keyword: " + keyword);
//
//            long id = keywords.get().getId();
//            List<BlacklistResult> blacklistedKeyword = blacklistedWordsService.findBlacklistedKeyword(keyword);
//
//            if(blacklistedKeyword.size() > 0 )
//            {
//                String foundBlacklistedKeyword = blacklistedKeyword.get(0).getTrademark();
//
//                System.out.println("Found blacklisted word " + foundBlacklistedKeyword + " in keyword: " + keyword);
//
//                processedWordsService.updateTrademarkBlacklistedById(id, foundBlacklistedKeyword);
//            }
//        }

        Optional<ProcessedWords> keywords = processedWordsService.findRandomKeywordWhereTrademarkBlacklistedIsEmpty();

        if(keywords.isPresent())
        {
            List<BlacklistedWords> blacklistedWords = blacklistedWordsService.findAll();
            List<String> list = new ArrayList<>();
            for(BlacklistedWords item:  blacklistedWords){
                list.add(item.getKeyword());
            }

            ProcessedWords processedWords = keywords.get();
            String keyword = processedWords.getKeyword();

            if(list.contains(keyword))
            {
                System.out.println("Found blacklisted word in keyword: " + keyword);
            }

        }
    }
}
