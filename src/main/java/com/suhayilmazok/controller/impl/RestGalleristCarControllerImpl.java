package com.suhayilmazok.controller.impl;

import com.suhayilmazok.controller.IRestGalleristCarController;
import com.suhayilmazok.controller.RestBaseController;
import com.suhayilmazok.controller.RootEntity;
import com.suhayilmazok.dto.DtoGalleristCar;
import com.suhayilmazok.dto.DtoGalleristCarIU;
import com.suhayilmazok.service.IGalleristCarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest/api/gallerist-car")
@RestController
public class RestGalleristCarControllerImpl extends RestBaseController  implements IRestGalleristCarController {

    @Autowired
    private IGalleristCarService galleristCarService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoGalleristCar> saveGalleristCar(@Valid @RequestBody DtoGalleristCarIU dtoGalleristCarIU) {
        return ok(galleristCarService.saveGalleristCar(dtoGalleristCarIU));
    }
}
