package com.suhayilmazok.dto;

import lombok.Data;

@Data
public class DtoSaledCar extends DtoBase{

    private DtoCustomer customer;

    private DtoCar car;

    private DtoGallerist gallerist;
}
