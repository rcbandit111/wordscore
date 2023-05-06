package com.wordscore.engine.processor;

import com.wordscore.engine.database.entity.BlacklistedWords;
import com.wordscore.engine.database.entity.ProcessedWords;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BlacklistWordCheckJob extends ServiceFactory implements Job {

    public BlacklistWordCheckJob() {
    }

    @Override
    public void execute(JobExecutionContext context) {

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

    List<String> findPhrasesInDocument(String document, List<String> phrases) {
        return phrases
                .stream()
                .filter(p -> Pattern.compile("(^|\\s)"+ Pattern.quote(p.toLowerCase()) + "(\\s|$)")
                        .asPredicate()
                        .test(document.toLowerCase()))
                .collect(Collectors.toList());
    }
}
