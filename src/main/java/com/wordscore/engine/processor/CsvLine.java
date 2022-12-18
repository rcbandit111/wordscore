package com.wordscore.engine.processor;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CsvLine {

    @CsvBindByPosition(position = 0)
    private String keyword;

    @CsvBindByPosition(position = 1)
    private String currency;

    @CsvBindByPosition(position = 2)
    private BigDecimal avgMonthlySearches;

    @CsvBindByPosition(position = 7)
    private BigDecimal lowRange;

    @CsvBindByPosition(position = 8)
    private BigDecimal highRange;
}
