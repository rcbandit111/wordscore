package com.wordscore.engine.rest.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class FindKeywordRequestDTO {

    private String keyword;
}
