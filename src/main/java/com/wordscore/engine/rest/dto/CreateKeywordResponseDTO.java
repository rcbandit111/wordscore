package com.wordscore.engine.rest.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CreateKeywordResponseDTO {

    private String keyword;
    private LocalDateTime createdAt;
    private long seoScoreUk;
    private long seoScoreUs;
    private long volumeUs;
}
