package com.wordscore.engine.database.service;

import com.wordscore.engine.database.entity.ProcessedWords;
import com.wordscore.engine.rest.dto.UpdateKeywordRequestDTO;

import java.util.Optional;

public interface ProcessedWordsService {

    Optional<ProcessedWords> findByKeyword(String keyword);

    Optional<ProcessedWords> save(ProcessedWords entity);

    int update(UpdateKeywordRequestDTO updateKeywordRequestDTO);

    int updateBySeoScoreUs(UpdateKeywordRequestDTO updateKeywordRequestDTO);

    Optional<ProcessedWords> findRandomKeyword();

    Optional<ProcessedWords> findRandomKeywordWhereWordsCountIsEmpty();

    int updateKeywordCount(Integer count, String keyword);

    int updateComDomainById(long id, boolean isAvailable);

    int updateNetDomainById(long id, boolean isAvailable);

    int updateOrgDomainById(long id, boolean isAvailable);

    Optional<ProcessedWords> findByKeywordOrderByOldestApiRequestedAt();
}
