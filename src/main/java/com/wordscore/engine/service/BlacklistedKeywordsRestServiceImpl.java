package com.wordscore.engine.service;

import com.wordscore.engine.database.entity.BlacklistedWords;
import com.wordscore.engine.database.service.BlacklistedWordsService;
import com.wordscore.engine.rest.dto.CreateBlacklistedKeywordRequestDTO;
import com.wordscore.engine.rest.dto.FindBlacklistedKeywordRequestDTO;
import com.wordscore.engine.rest.dto.FindBlacklistedKeywordResponseDTO;
import com.wordscore.engine.rest.mapper.BlacklistedKeywordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BlacklistedKeywordsRestServiceImpl implements BlacklistedKeywordsRestService {

    private BlacklistedWordsService blacklistedWordsService;
    private BlacklistedKeywordMapper blacklistedkeywordMapper;

    @Autowired
    public BlacklistedKeywordsRestServiceImpl(BlacklistedWordsService blacklistedWordsService, BlacklistedKeywordMapper blacklistedkeywordMapper) {
        this.blacklistedWordsService = blacklistedWordsService;
        this.blacklistedkeywordMapper = blacklistedkeywordMapper;
    }

    @Override
    public Optional<FindBlacklistedKeywordResponseDTO> findKeyword(FindBlacklistedKeywordRequestDTO findBlacklistedKeywordRequestDTO) {
        return blacklistedWordsService.findByKeyword(findBlacklistedKeywordRequestDTO.getKeyword()).map(blacklistedkeywordMapper::toFindBlacklistedKeywordDTO);
    }

    @Override
    public Optional<FindBlacklistedKeywordResponseDTO> createKeyword(CreateBlacklistedKeywordRequestDTO createBlacklistedKeywordRequestDTO) {

        Optional<BlacklistedWords> queryResult = blacklistedWordsService.findByKeyword(createBlacklistedKeywordRequestDTO.getKeyword());
        if(!queryResult.isPresent()){
            BlacklistedWords obj = BlacklistedWords.builder()
                    .keyword(createBlacklistedKeywordRequestDTO.getKeyword())
                    .createdAt(LocalDateTime.now())
                    .build();
            return blacklistedWordsService.save(obj).map(blacklistedkeywordMapper::toCreateBlacklistedKeywordDTO);
        } else {
            return Optional.empty();
        }
    }
}
