package com.suhayilmazok.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class DtoCustomer extends DtoBase{


    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String tckn;

    @NotNull
    private Date dateOfBirth;

    @NotNull
    private DtoAddress address;

    @NotNull
    private DtoAccount account;
}
