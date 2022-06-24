package com.wordscore.engine.service;

import com.wordscore.engine.rest.dto.*;

import java.util.Optional;

public interface BlacklistedKeywordsRestService {

    Optional<FindBlacklistedKeywordResponseDTO> findKeyword(FindBlacklistedKeywordRequestDTO findBlacklistedKeywordRequestDTO);

    Optional<FindBlacklistedKeywordResponseDTO> createKeyword(CreateBlacklistedKeywordRequestDTO createBlacklistedKeywordRequestDTO);
}
