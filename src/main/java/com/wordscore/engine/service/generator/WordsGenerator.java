package com.wordscore.engine.service.generator;

import java.util.List;

public interface WordsGenerator {

    String generateRandomString(int minLength, int maxLength);

    List<String> generateRandomKeyword();
}
