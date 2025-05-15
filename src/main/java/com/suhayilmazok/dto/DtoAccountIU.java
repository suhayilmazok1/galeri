package com.suhayilmazok.dto;

import com.suhayilmazok.enums.CurrencyType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DtoAccountIU {

    @NotNull
    private String accountNo;

    @NotNull
    private String iban;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private CurrencyType currencyType;
}
