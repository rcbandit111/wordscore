package com.wordscore.engine.rest.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class FindKeywordResponseDTO {

    private String keyword;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private long seoScoreUk;
    private long seoScoreUs;
    private long volumeUs;
}
