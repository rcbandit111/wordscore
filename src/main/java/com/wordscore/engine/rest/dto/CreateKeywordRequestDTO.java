package com.wordscore.engine.rest.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CreateKeywordRequestDTO {

    private String keyword;
}
