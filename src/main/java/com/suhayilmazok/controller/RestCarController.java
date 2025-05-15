package com.suhayilmazok.controller;

import com.suhayilmazok.dto.DtoCar;
import com.suhayilmazok.dto.DtoCarIU;

import java.util.List;

public interface RestCarController {

    public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);

    public RootEntity<DtoCar> getCar(Long id);

    public RootEntity<List<DtoCar>> getAllCars();

    public void deleteCar(Long id);

    public RootEntity<DtoCar> updateCar(Long id ,DtoCarIU dtoCarIU);
}
