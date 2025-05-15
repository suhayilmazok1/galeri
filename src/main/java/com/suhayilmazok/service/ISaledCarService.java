package com.suhayilmazok.service;

import com.suhayilmazok.dto.DtoSaledCar;
import com.suhayilmazok.dto.DtoSaledCarIU;

public interface ISaledCarService {

    public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU);
}
