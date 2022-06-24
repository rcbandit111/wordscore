package com.wordscore.engine.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FindBlacklistedKeywordResponseDTO {

    private String keyword;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isBlacklisted;
}
