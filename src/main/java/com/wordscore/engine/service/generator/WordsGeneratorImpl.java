package com.wordscore.engine.service.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import  com.github.dhiraj072.randomwordgenerator.RandomWordGenerator;


import java.io.IOException;

@Service
public class WordsGeneratorImpl implements WordsGenerator {

    @Override
    public String generateRandomString(int minLength, int maxLength) {
        return RandomStringUtils.randomAlphabetic(minLength, maxLength);
    }

    @Override
    public String generateRandomKeyword() {

        String word = RandomWordGenerator.getRandomWord();


//        OkHttpClient httpClient = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url("https://random-words-api.vercel.app/word")
//                .get()
//                .build();
//
//        String word = null;
//        try (Response response = httpClient.newCall(request).execute()) {
//
//            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//            // Get response body
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            String responseBody = response.body().string();
//            response.body().close();
//            Root[] root = objectMapper.readValue(responseBody, Root[].class);
//
//            for (Root dd : root) {
//                word = dd.word.toLowerCase();
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        return word;
    }
}
