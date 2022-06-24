package com.wordscore.engine.rest.mapper;

import com.wordscore.engine.database.entity.BlacklistedWords;
import com.wordscore.engine.rest.dto.FindBlacklistedKeywordResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-25T01:30:09+0300",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.1.jar, environment: Java 17 (Oracle Corporation)"
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
