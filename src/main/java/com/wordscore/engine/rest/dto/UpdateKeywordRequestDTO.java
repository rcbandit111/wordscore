package com.wordscore.engine.rest.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UpdateKeywordRequestDTO {

    private String keyword;
    private int seoScoreUk;
    private int seoScoreUs;
    private int volumeUs;
}
