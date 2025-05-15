package com.suhayilmazok.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DtoSaledCarIU {

    @NotNull
    private Long customerId;

    @NotNull
    private Long carId;

    @NotNull
    private Long galleristId;
}
