package com.wordscore.engine.rest.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class GetRandomKeywordResponseDTO {

    private String keyword;

}
