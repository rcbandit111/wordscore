package com.wordscore.engine.service;

import com.wordscore.engine.database.entity.ProcessedWords;
import com.wordscore.engine.database.service.ProcessedWordsService;
import com.wordscore.engine.rest.dto.*;
import com.wordscore.engine.rest.mapper.KeywordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class KeywordsRestServiceImpl implements KeywordsRestService {

    private ProcessedWordsService processedWordsService;
    private KeywordMapper keywordMapper;

    @Autowired
    public KeywordsRestServiceImpl(ProcessedWordsService processedWordsService, KeywordMapper keywordMapper) {
        this.processedWordsService = processedWordsService;
        this.keywordMapper = keywordMapper;
    }

    @Override
    public Optional<FindKeywordResponseDTO> findKeyword(FindKeywordRequestDTO findKeywordRequestDTO) {
        return processedWordsService.findByKeyword(findKeywordRequestDTO.getKeyword()).map(keywordMapper::toFindKeywordDTO);
    }

    @Override
    public Optional<FindKeywordResponseDTO> createKeyword(CreateKeywordRequestDTO createKeywordRequestDTO) {

        Optional<ProcessedWords> queryResult = processedWordsService.findByKeyword(createKeywordRequestDTO.getKeyword());
        if(!queryResult.isPresent()){
            ProcessedWords obj = ProcessedWords.builder()
                    .keyword(createKeywordRequestDTO.getKeyword())
                    .createdAt(LocalDateTime.now())
                    .build();
            return processedWordsService.save(obj).map(keywordMapper::toCreateKeywordDTO);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public int updateKeyword(UpdateKeywordRequestDTO updateKeywordRequestDTO) {

        Optional<ProcessedWords> queryResult = processedWordsService.findByKeyword(updateKeywordRequestDTO.getKeyword());
        if(queryResult.isPresent()){
            return processedWordsService.update(updateKeywordRequestDTO);
        } else {
            return 0;
        }
    }
}
