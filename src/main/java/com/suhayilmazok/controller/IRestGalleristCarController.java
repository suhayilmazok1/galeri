package com.suhayilmazok.controller;

import com.suhayilmazok.dto.DtoGalleristCar;
import com.suhayilmazok.dto.DtoGalleristCarIU;

public interface IRestGalleristCarController {

    public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}
