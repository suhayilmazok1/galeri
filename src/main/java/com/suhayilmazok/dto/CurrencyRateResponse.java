package com.suhayilmazok.dto;

import lombok.Data;

import java.util.List;

@Data
public class CurrencyRateResponse {

    private Integer totalCount;

    private List<CurrencyRatesItems> items;
}
