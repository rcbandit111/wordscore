package com.wordscore.engine.rest.mapper;

import com.wordscore.engine.config.BaseMapperConfig;
import com.wordscore.engine.database.entity.ProcessedWords;
import com.wordscore.engine.rest.dto.FindKeywordResponseDTO;
import com.wordscore.engine.rest.dto.UpdateKeywordResponseDTO;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface KeywordMapper {

    FindKeywordResponseDTO toFindKeywordDTO(ProcessedWords processedWords);

    FindKeywordResponseDTO toCreateKeywordDTO(ProcessedWords processedWords);

    UpdateKeywordResponseDTO toUpdateKeywordDTO(ProcessedWords processedWords);
}
