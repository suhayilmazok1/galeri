package com.suhayilmazok.service;

import com.suhayilmazok.dto.CurrencyRateResponse;

public interface ICurrencyRatesService {

    public CurrencyRateResponse getCurrencyRates(String startDate, String endDate);
}
