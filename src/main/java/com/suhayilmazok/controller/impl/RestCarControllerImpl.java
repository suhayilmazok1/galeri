package com.suhayilmazok.controller.impl;

import com.suhayilmazok.controller.RestBaseController;
import com.suhayilmazok.controller.RestCarController;
import com.suhayilmazok.controller.RootEntity;
import com.suhayilmazok.dto.DtoCar;
import com.suhayilmazok.dto.DtoCarIU;
import com.suhayilmazok.service.ICarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/car")
public class RestCarControllerImpl extends RestBaseController implements RestCarController {

    @Autowired
    private ICarService carService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoCar> saveCar(@Valid @RequestBody DtoCarIU dtoCarIU) {
        return ok(carService.saveCar(dtoCarIU));
    }

    @GetMapping("/list/{id}")
    @Override
    public RootEntity<DtoCar> getCar(@PathVariable(name = "id") Long id) {
        return ok(carService.getCar(id));
    }

    @GetMapping("/list")
    @Override
    public RootEntity<List<DtoCar>> getAllCars() {
        return ok(carService.getAllCars());
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public void deleteCar(@PathVariable(name = "id") Long id) {
        carService.deleteCar(id);
    }

    @PutMapping("/update/{id}")
    @Override
    public RootEntity<DtoCar> updateCar(@PathVariable(name = "id") Long id,@RequestBody @Valid DtoCarIU dtoCarIU) {
        return ok(carService.updateCar(id,dtoCarIU));
    }
}
