package com.wordscore.engine.rest.mapper;

import com.wordscore.engine.config.BaseMapperConfig;
import com.wordscore.engine.database.entity.BlacklistedWords;
import com.wordscore.engine.rest.dto.FindBlacklistedKeywordResponseDTO;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface BlacklistedKeywordMapper {

    FindBlacklistedKeywordResponseDTO toFindBlacklistedKeywordDTO(BlacklistedWords blacklistedWords);

    FindBlacklistedKeywordResponseDTO toCreateBlacklistedKeywordDTO(BlacklistedWords blacklistedWords);
}
