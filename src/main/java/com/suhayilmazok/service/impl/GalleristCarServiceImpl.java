package com.suhayilmazok.service.impl;

import com.suhayilmazok.dto.*;
import com.suhayilmazok.exception.BaseException;
import com.suhayilmazok.exception.ErrorMessage;
import com.suhayilmazok.exception.MessageType;
import com.suhayilmazok.model.Car;
import com.suhayilmazok.model.Gallerist;
import com.suhayilmazok.model.GalleristCar;
import com.suhayilmazok.repository.CarRepository;
import com.suhayilmazok.repository.GalleristCarRepository;
import com.suhayilmazok.repository.GalleristRepository;
import com.suhayilmazok.service.IGalleristCarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class GalleristCarServiceImpl implements IGalleristCarService {

    @Autowired
    GalleristCarRepository galleristCarRepository;

    @Autowired
    GalleristRepository galleristRepository;

    @Autowired
    CarRepository carRepository;

    private GalleristCar createGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {

        Optional<Gallerist> optGallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId());
        if(optGallerist.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getGalleristId().toString()));
        }
        Optional<Car> optCar = carRepository.findById(dtoGalleristCarIU.getCarId());
        if(optCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getCarId().toString()));
        }

        GalleristCar galleristCar = new GalleristCar();
        galleristCar.setCreateTime(new Date());
        galleristCar.setGallerist(optGallerist.get());
        galleristCar.setCar(optCar.get());
        return galleristCar;
    }

    @Override
    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
        DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoCar dtoCar = new DtoCar();

        DtoAddress dtoAddress = new DtoAddress();

        GalleristCar savedGalleristCar = galleristCarRepository.save(createGalleristCar(dtoGalleristCarIU));

        BeanUtils.copyProperties(savedGalleristCar, dtoGalleristCar);
        BeanUtils.copyProperties(savedGalleristCar.getGallerist(), dtoGallerist);
        BeanUtils.copyProperties(savedGalleristCar.getGallerist().getAddress(), dtoAddress);

        BeanUtils.copyProperties(savedGalleristCar.getCar(), dtoCar);

        dtoGallerist.setAddress(dtoAddress);
        dtoGalleristCar.setGallerist(dtoGallerist);
        dtoGalleristCar.setCar(dtoCar);

        return dtoGalleristCar;
    }
}
