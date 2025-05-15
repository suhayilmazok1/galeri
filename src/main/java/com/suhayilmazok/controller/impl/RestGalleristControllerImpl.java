package com.suhayilmazok.controller.impl;

import com.suhayilmazok.controller.RestBaseController;
import com.suhayilmazok.controller.RestGalleristController;
import com.suhayilmazok.controller.RootEntity;
import com.suhayilmazok.dto.DtoGallerist;
import com.suhayilmazok.dto.DtoGalleristIU;
import com.suhayilmazok.service.IGalleristService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/rest/api/gallerist")
@RestController
public class RestGalleristControllerImpl extends RestBaseController implements RestGalleristController {

    @Autowired
    private IGalleristService galleristService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoGallerist> saveGallerist(@Valid @RequestBody DtoGalleristIU dtoGalleristIU) {
        return ok(galleristService.saveGallerist(dtoGalleristIU));
    }

    @GetMapping("/list/{id}")
    @Override
    public RootEntity<DtoGallerist> getGallerist(@PathVariable(name = "id") Long id) {
        return ok(galleristService.getGallerist(id));
    }

    @GetMapping("/list")
    @Override
    public RootEntity<List<DtoGallerist>> getAllGallerists() {
        return ok(galleristService.getAllGallerist());
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public void deleteGallerist(@PathVariable(name = "id") Long id) {
        galleristService.deleteGallerist(id);
    }

    @PutMapping("/update/{id}")
    @Override
    public RootEntity<DtoGallerist> updateGallerist(@PathVariable(name = "id") Long id,@Valid @RequestBody DtoGalleristIU dtoGalleristIU) {
        return ok(galleristService.updateGallerist(id,dtoGalleristIU));
    }
}
