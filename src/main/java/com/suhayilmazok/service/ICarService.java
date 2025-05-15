package com.suhayilmazok.service;

import com.suhayilmazok.dto.DtoCar;
import com.suhayilmazok.dto.DtoCarIU;

import java.util.List;

public interface ICarService {

    public DtoCar saveCar(DtoCarIU dtoCarIU);

    public DtoCar getCar(Long id);

    public List<DtoCar> getAllCars();

    public void deleteCar(Long id);

    public DtoCar updateCar(Long id ,DtoCarIU dtoCarIU);
}
