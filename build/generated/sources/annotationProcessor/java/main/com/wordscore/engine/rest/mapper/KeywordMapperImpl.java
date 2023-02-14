package com.wordscore.engine.rest.mapper;

import com.wordscore.engine.database.entity.ProcessedWords;
import com.wordscore.engine.rest.dto.FindKeywordResponseDTO;
import com.wordscore.engine.rest.dto.GetRandomKeywordResponseDTO;
import com.wordscore.engine.rest.dto.UpdateKeywordResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-14T16:15:11+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.0.jar, environment: Java 17 (Oracle Corporation)"
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
        if ( processedWords.getSeoScoreUk() != null ) {
            findKeywordResponseDTO.seoScoreUk( processedWords.getSeoScoreUk().longValue() );
        }
        if ( processedWords.getSeoScoreUs() != null ) {
            findKeywordResponseDTO.seoScoreUs( processedWords.getSeoScoreUs().longValue() );
        }
        if ( processedWords.getVolumeUs() != null ) {
            findKeywordResponseDTO.volumeUs( processedWords.getVolumeUs().longValue() );
        }

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
        if ( processedWords.getSeoScoreUk() != null ) {
            findKeywordResponseDTO.seoScoreUk( processedWords.getSeoScoreUk().longValue() );
        }
        if ( processedWords.getSeoScoreUs() != null ) {
            findKeywordResponseDTO.seoScoreUs( processedWords.getSeoScoreUs().longValue() );
        }
        if ( processedWords.getVolumeUs() != null ) {
            findKeywordResponseDTO.volumeUs( processedWords.getVolumeUs().longValue() );
        }

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
        if ( processedWords.getSeoScoreUk() != null ) {
            updateKeywordResponseDTO.seoScoreUk( processedWords.getSeoScoreUk().intValue() );
        }
        if ( processedWords.getSeoScoreUs() != null ) {
            updateKeywordResponseDTO.seoScoreUs( processedWords.getSeoScoreUs().intValue() );
        }
        if ( processedWords.getVolumeUs() != null ) {
            updateKeywordResponseDTO.volumeUs( processedWords.getVolumeUs().intValue() );
        }

        return updateKeywordResponseDTO.build();
    }

    @Override
    public GetRandomKeywordResponseDTO toRandomKeywordDTO(ProcessedWords processedWords) {
        if ( processedWords == null ) {
            return null;
        }

        GetRandomKeywordResponseDTO.GetRandomKeywordResponseDTOBuilder getRandomKeywordResponseDTO = GetRandomKeywordResponseDTO.builder();

        getRandomKeywordResponseDTO.keyword( processedWords.getKeyword() );

        return getRandomKeywordResponseDTO.build();
    }
}
