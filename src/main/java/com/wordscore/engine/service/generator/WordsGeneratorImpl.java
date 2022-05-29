package com.wordscore.engine.service.generator;

import com.maximeroussy.invitrode.WordGenerator;
import com.szadowsz.datamuse.DatamuseClient;
import com.szadowsz.datamuse.DatamuseException;
import com.szadowsz.datamuse.DatamuseParam;
import com.szadowsz.datamuse.WordResult;
import kotlin.collections.ArrayDeque;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class WordsGeneratorImpl implements WordsGenerator {

    @Override
    public String generateRandomString(int minLength, int maxLength) {
        return RandomStringUtils.randomAlphabetic(minLength, maxLength);
    }

    @Override
    public List<String> generateRandomKeyword() {

        WordGenerator generator = new WordGenerator();
        String myNewWord = generator.newWord(5);


        DatamuseClient dmuse = new DatamuseClient();
        Map<DatamuseParam.Code, String> options = new HashMap<>();
        options.put(DatamuseParam.Code.REL_JJA, "yellow");
        try {
            List<WordResult> results = dmuse.complexQuery(options);

            List<String> endResult = new ArrayDeque<>();
            for (WordResult result : results) {
                endResult.add(result.getWord());
            }

            return endResult;
        } catch (DatamuseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
