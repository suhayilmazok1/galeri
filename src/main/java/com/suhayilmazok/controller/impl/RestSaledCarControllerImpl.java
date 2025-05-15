package com.suhayilmazok.controller.impl;

import com.suhayilmazok.controller.IRestSaledCarController;
import com.suhayilmazok.controller.RestBaseController;
import com.suhayilmazok.controller.RootEntity;
import com.suhayilmazok.dto.DtoSaledCar;
import com.suhayilmazok.dto.DtoSaledCarIU;
import com.suhayilmazok.service.ISaledCarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/saled-car")
public class RestSaledCarControllerImpl extends RestBaseController implements IRestSaledCarController {

    @Autowired
    private ISaledCarService saledCarService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoSaledCar> buyCar(@Valid @RequestBody DtoSaledCarIU saledCar) {
        return ok(saledCarService.buyCar(saledCar));
    }
}
