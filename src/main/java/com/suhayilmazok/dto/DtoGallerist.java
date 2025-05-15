package com.suhayilmazok.dto;

import com.suhayilmazok.model.Address;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class DtoGallerist extends DtoBase{

    private String firstName;

    private String lastName;

    private DtoAddress address;
}
