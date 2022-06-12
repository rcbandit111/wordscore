package com.wordscore.engine.rest.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UpdateKeywordResponseDTO {

    private String keyword;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int seoScoreUk;
    private int seoScoreUs;
    private int volumeUs;
}
