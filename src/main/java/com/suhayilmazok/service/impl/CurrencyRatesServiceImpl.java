package com.suhayilmazok.service.impl;

import com.suhayilmazok.dto.CurrencyRateResponse;
import com.suhayilmazok.exception.BaseException;
import com.suhayilmazok.exception.ErrorMessage;
import com.suhayilmazok.exception.MessageType;
import com.suhayilmazok.service.ICurrencyRatesService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyRatesServiceImpl implements ICurrencyRatesService {

    @Override
    public CurrencyRateResponse getCurrencyRates(String startDate, String endDate) {

        String rootURL = "https://evds2.tcmb.gov.tr/service/evds/";
        String series = "TP.DK.USD.A";
        String type = "json";
        String endpoint = rootURL + "series=" + series + "&startDate=" + startDate + "&endDate=" + endDate + "&type=" + type;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("key", "LXTkFSwKZr");

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<CurrencyRateResponse> response = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity, CurrencyRateResponse.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.CURRENCY_NOT_FOUND, e.getMessage()));
        }
        return null;
    }
}
