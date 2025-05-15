package com.suhayilmazok.dto;

import com.suhayilmazok.model.Address;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoGalleristIU {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private Long addressId;
}
