package com.suhayilmazok.controller.impl;

import com.suhayilmazok.controller.IRestAddressController;
import com.suhayilmazok.controller.RestBaseController;
import com.suhayilmazok.controller.RootEntity;
import com.suhayilmazok.dto.DtoAddress;
import com.suhayilmazok.dto.DtoAddressIU;
import com.suhayilmazok.service.IAddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/address")
public class RestAddressControllerImpl extends RestBaseController implements IRestAddressController {

    @Autowired
    private IAddressService addressService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoAddress> saveAddress(@Valid @RequestBody DtoAddressIU dtoAddressIU) {
        return ok(addressService.saveAddress(dtoAddressIU));
    }

    @GetMapping(path = "/list/{id}")
    @Override
    public RootEntity<DtoAddress> findAddressById(@PathVariable("id") Long id) {
        return ok(addressService.getAddressesByID(id));
    }

    @GetMapping(path = "/list")
    @Override
    public RootEntity<List<DtoAddress>> findAllAddresses() {

        return ok(addressService.getAllAddresses());
    }

    @DeleteMapping(path = "/delete/{id}")
    @Override
    public void deleteAddress(@PathVariable( name = "id") Long id) {
        addressService.deleteAddress(id);
    }

    @PutMapping(path = "/update/{id}")
    @Override
    public RootEntity<DtoAddress> updateAddress(@PathVariable(name = "id") Long id, @Valid @RequestBody DtoAddressIU dtoAddressIU) {
        return ok(addressService.updateAddress(id, dtoAddressIU));
    }
}
