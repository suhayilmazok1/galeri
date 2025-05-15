package com.suhayilmazok.dto;

import com.suhayilmazok.model.Account;
import com.suhayilmazok.model.Address;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class DtoCustomerIU {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String tckn;

    @NotNull
    private Date dateOfBirth;

    @NotNull
    private Long addressId;

    @NotNull
    private Long accountId;
}
