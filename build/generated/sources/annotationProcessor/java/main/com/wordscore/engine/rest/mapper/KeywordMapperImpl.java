package com.wordscore.engine.rest.mapper;

import com.wordscore.engine.database.entity.ProcessedWords;
import com.wordscore.engine.rest.dto.FindKeywordResponseDTO;
import com.wordscore.engine.rest.dto.UpdateKeywordResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-12T21:39:54+0300",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.1.jar, environment: Java 17 (Oracle Corporation)"
)
@Component
public class KeywordMapperImpl implements KeywordMapper {

    @Override
    public FindKeywordResponseDTO toFindKeywordDTO(ProcessedWords processedWords) {
        if ( processedWords == null ) {
            return null;
        }

        FindKeywordResponseDTO.FindKeywordResponseDTOBuilder findKeywordResponseDTO = FindKeywordResponseDTO.builder();

        findKeywordResponseDTO.keyword( processedWords.getKeyword() );
        findKeywordResponseDTO.createdAt( processedWords.getCreatedAt() );
        findKeywordResponseDTO.updatedAt( processedWords.getUpdatedAt() );
        findKeywordResponseDTO.seoScoreUk( processedWords.getSeoScoreUk() );
        findKeywordResponseDTO.seoScoreUs( processedWords.getSeoScoreUs() );
        findKeywordResponseDTO.volumeUs( processedWords.getVolumeUs() );

        return findKeywordResponseDTO.build();
    }

    @Override
    public FindKeywordResponseDTO toCreateKeywordDTO(ProcessedWords processedWords) {
        if ( processedWords == null ) {
            return null;
        }

        FindKeywordResponseDTO.FindKeywordResponseDTOBuilder findKeywordResponseDTO = FindKeywordResponseDTO.builder();

        findKeywordResponseDTO.keyword( processedWords.getKeyword() );
        findKeywordResponseDTO.createdAt( processedWords.getCreatedAt() );
        findKeywordResponseDTO.updatedAt( processedWords.getUpdatedAt() );
        findKeywordResponseDTO.seoScoreUk( processedWords.getSeoScoreUk() );
        findKeywordResponseDTO.seoScoreUs( processedWords.getSeoScoreUs() );
        findKeywordResponseDTO.volumeUs( processedWords.getVolumeUs() );

        return findKeywordResponseDTO.build();
    }

    @Override
    public UpdateKeywordResponseDTO toUpdateKeywordDTO(ProcessedWords processedWords) {
        if ( processedWords == null ) {
            return null;
        }

        UpdateKeywordResponseDTO.UpdateKeywordResponseDTOBuilder updateKeywordResponseDTO = UpdateKeywordResponseDTO.builder();

        updateKeywordResponseDTO.keyword( processedWords.getKeyword() );
        updateKeywordResponseDTO.createdAt( processedWords.getCreatedAt() );
        updateKeywordResponseDTO.updatedAt( processedWords.getUpdatedAt() );
        updateKeywordResponseDTO.seoScoreUk( (int) processedWords.getSeoScoreUk() );
        updateKeywordResponseDTO.seoScoreUs( (int) processedWords.getSeoScoreUs() );
        updateKeywordResponseDTO.volumeUs( (int) processedWords.getVolumeUs() );

        return updateKeywordResponseDTO.build();
    }
}
