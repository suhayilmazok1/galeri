package com.suhayilmazok.controller;

import com.suhayilmazok.dto.DtoSaledCar;
import com.suhayilmazok.dto.DtoSaledCarIU;

public interface IRestSaledCarController {
    public RootEntity<DtoSaledCar> buyCar(DtoSaledCarIU saledCar);
}
