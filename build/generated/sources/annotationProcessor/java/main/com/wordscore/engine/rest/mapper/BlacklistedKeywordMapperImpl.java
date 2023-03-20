package com.wordscore.engine.rest.mapper;

import com.wordscore.engine.database.entity.BlacklistedWords;
import com.wordscore.engine.rest.dto.FindBlacklistedKeywordResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-20T16:33:26+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.0.1.jar, environment: Java 17 (Oracle Corporation)"
)
@Component
public class BlacklistedKeywordMapperImpl implements BlacklistedKeywordMapper {

    @Override
    public FindBlacklistedKeywordResponseDTO toFindBlacklistedKeywordDTO(BlacklistedWords blacklistedWords) {
        if ( blacklistedWords == null ) {
            return null;
        }

        FindBlacklistedKeywordResponseDTO.FindBlacklistedKeywordResponseDTOBuilder findBlacklistedKeywordResponseDTO = FindBlacklistedKeywordResponseDTO.builder();

        findBlacklistedKeywordResponseDTO.keyword( blacklistedWords.getKeyword() );
        findBlacklistedKeywordResponseDTO.createdAt( blacklistedWords.getCreatedAt() );
        findBlacklistedKeywordResponseDTO.updatedAt( blacklistedWords.getUpdatedAt() );

        return findBlacklistedKeywordResponseDTO.build();
    }

    @Override
    public FindBlacklistedKeywordResponseDTO toCreateBlacklistedKeywordDTO(BlacklistedWords blacklistedWords) {
        if ( blacklistedWords == null ) {
            return null;
        }

        FindBlacklistedKeywordResponseDTO.FindBlacklistedKeywordResponseDTOBuilder findBlacklistedKeywordResponseDTO = FindBlacklistedKeywordResponseDTO.builder();

        findBlacklistedKeywordResponseDTO.keyword( blacklistedWords.getKeyword() );
        findBlacklistedKeywordResponseDTO.createdAt( blacklistedWords.getCreatedAt() );
        findBlacklistedKeywordResponseDTO.updatedAt( blacklistedWords.getUpdatedAt() );

        return findBlacklistedKeywordResponseDTO.build();
    }
}
