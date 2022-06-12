package com.wordscore.engine.service.generator;

public interface WordsGenerator {

    String generateRandomString(int minLength, int maxLength);

    String generateRandomKeyword();
}
