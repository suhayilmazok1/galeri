package com.suhayilmazok.service.impl;

import com.suhayilmazok.dto.DtoCar;
import com.suhayilmazok.dto.DtoCarIU;
import com.suhayilmazok.model.Car;
import com.suhayilmazok.repository.CarRepository;
import com.suhayilmazok.service.ICarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements ICarService {

    @Autowired
    private CarRepository carRepository;

    private Car createCar(DtoCarIU dtoCarIU) {
        Car car = new Car();
        car.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoCarIU, car);
        return car;
    }

    @Override
    public DtoCar saveCar(DtoCarIU dtoCarIU) {

        DtoCar dtoCar = new DtoCar();
        Car savedCar = carRepository.save(createCar(dtoCarIU));
        BeanUtils.copyProperties(savedCar, dtoCar);
        return dtoCar;
    }

    @Override
    public DtoCar getCar(Long id) {
        DtoCar dtoCar = new DtoCar();
        Optional<Car> optCar = carRepository.findById(id);
        if(optCar.isPresent()) {
            Car car = optCar.get();
            BeanUtils.copyProperties(car, dtoCar);
            return dtoCar;
        }
        return null;
    }

    @Override
    public List<DtoCar> getAllCars() {
        List<DtoCar> dtoCar = new ArrayList<>();
        List<Car> carList = carRepository.findAll();

        for (Car car : carList) {
            DtoCar dtoCarDto = new DtoCar();
            BeanUtils.copyProperties(car, dtoCarDto);
            dtoCar.add(dtoCarDto);

        }
        return dtoCar;
    }

    @Override
    public void deleteCar(Long id) {
        Optional<Car> optCar = carRepository.findById(id);
        if(optCar.isPresent()) {
            Car car = optCar.get();
            carRepository.delete(car);
        }
    }

    @Override
    public DtoCar updateCar(Long id, DtoCarIU dtoCarIU) {

        DtoCar dtoCar = new DtoCar();

        Optional<Car> optCar = carRepository.findById(id);

        if(optCar.isPresent()) {
            Car car = optCar.get();

            car.setCreateTime(new Date());
            car.setCarStatusType(dtoCarIU.getCarStatusType());
            car.setModel(dtoCarIU.getModel());
            car.setPrice(dtoCarIU.getPrice());
            car.setBrand(dtoCarIU.getBrand());
            car.setCurrencyType(dtoCarIU.getCurrencyType());
            car.setDamagePrice(dtoCarIU.getDamagePrice());
            car.setPlaka(dtoCarIU.getPlaka());
            car.setProductionYear(dtoCarIU.getProductionYear());

            carRepository.save(car);
            BeanUtils.copyProperties(car, dtoCar);
            return dtoCar;
        }
        return null;
    }
}
