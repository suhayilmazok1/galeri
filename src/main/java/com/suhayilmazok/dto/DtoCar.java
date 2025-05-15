package com.suhayilmazok.dto;

import com.suhayilmazok.enums.CarStatusType;
import com.suhayilmazok.enums.CurrencyType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DtoCar extends DtoBase{
    private String plaka;

    private String brand;

    private String model;

    private String productionYear;

    private BigDecimal price;

    private CurrencyType currencyType;

    private BigDecimal damagePrice;

    private CarStatusType carStatusType;
}
