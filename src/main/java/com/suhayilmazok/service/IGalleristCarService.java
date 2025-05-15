package com.suhayilmazok.service;

import com.suhayilmazok.dto.DtoGalleristCar;
import com.suhayilmazok.dto.DtoGalleristCarIU;

public interface IGalleristCarService {

    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}
