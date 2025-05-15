package com.suhayilmazok.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class DtoAddress extends DtoBase {

    private String city;

    private String district;

    private String neighborhood;

    private String street;
}
