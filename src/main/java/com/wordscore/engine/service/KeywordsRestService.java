package com.wordscore.engine.service;

import com.wordscore.engine.rest.dto.*;

import java.util.Optional;

public interface KeywordsRestService {

    Optional<FindKeywordResponseDTO> findKeyword(FindKeywordRequestDTO findKeywordRequestDTO);

    Optional<FindKeywordResponseDTO> createKeyword(CreateKeywordRequestDTO createKeywordRequestDTO);

    int updateKeyword(UpdateKeywordRequestDTO updateKeywordRequestDTO);

    Optional<GetRandomKeywordResponseDTO> getRandomKeyword();
}
