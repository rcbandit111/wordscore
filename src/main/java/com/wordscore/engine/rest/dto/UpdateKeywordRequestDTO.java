package com.wordscore.engine.rest.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UpdateKeywordRequestDTO {

    private String keyword;
    private BigDecimal seoScoreUk;
    private BigDecimal seoScoreUs;
    private BigDecimal lowRange;
    private BigDecimal highRange;
    private BigDecimal volumeUs;
}
