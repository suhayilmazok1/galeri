package com.suhayilmazok.dto;

import com.suhayilmazok.enums.CurrencyType;
import com.suhayilmazok.model.BaseEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DtoAccount extends DtoBase {

    private String accountNo;

    private String iban;

    private BigDecimal amount;

    private CurrencyType currencyType;
}
