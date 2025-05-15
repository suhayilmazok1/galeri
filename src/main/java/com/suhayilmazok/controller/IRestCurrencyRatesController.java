package com.suhayilmazok.controller;

import com.suhayilmazok.dto.CurrencyRateResponse;

public interface IRestCurrencyRatesController {

    public RootEntity<CurrencyRateResponse> getCurrencyRates(String startDate, String endDate);
}
