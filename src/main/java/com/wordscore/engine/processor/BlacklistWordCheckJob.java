package com.wordscore.engine.processor;

import com.wordscore.engine.database.entity.BlacklistedWords;
import com.wordscore.engine.database.entity.ProcessedWords;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

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
            List<String> blacklistedWordslist = new ArrayList<>();
            for(BlacklistedWords item:  blacklistedWords)
            {
                blacklistedWordslist.add(item.getKeyword());
            }

            ProcessedWords processedWords = keywords.get();
            String keyword = processedWords.getKeyword();

            System.out.println("Checking keyword: " + keyword);

            List<String> phrasesInDocument = findPhrasesInDocument(keyword, blacklistedWordslist);

            if(!phrasesInDocument.isEmpty())
            {
                System.out.println("Found blacklisted word in keyword: " + String.join(", ", phrasesInDocument));

                processedWordsService.updateTrademarkBlacklistedById(processedWords.getId(), String.join(", ", phrasesInDocument));
            }
        }
    }

    List<String> findPhrasesInDocument(String doc, List<String> phrases) {
        List<String> foundPhrases = new ArrayList<>();
        for (String phrase : phrases) {
            if (doc.indexOf(phrase) != -1) {
                foundPhrases.add(phrase);
            }
        }
        return foundPhrases;
    }
}
