package com.wordscore.engine.database.service;

import com.wordscore.engine.database.entity.ProcessedWords;
import com.wordscore.engine.rest.dto.UpdateKeywordRequestDTO;

import java.util.Optional;

public interface ProcessedWordsService {

    Optional<ProcessedWords> findByKeyword(String keyword);

    Optional<ProcessedWords> save(ProcessedWords entity);

    int update(UpdateKeywordRequestDTO updateKeywordRequestDTO);
}
