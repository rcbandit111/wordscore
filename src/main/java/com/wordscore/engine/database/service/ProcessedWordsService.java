package com.wordscore.engine.database.service;

import com.wordscore.engine.database.entity.ProcessedWords;

import java.util.Optional;

public interface ProcessedWordsService {

    Optional<ProcessedWords> findByName(String name);

    Optional<ProcessedWords> findByGoogleScore(long googleScore);

    void save(ProcessedWords entity);
}
