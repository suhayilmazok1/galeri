package com.suhayilmazok.controller.impl;

import com.suhayilmazok.controller.IRestCustomerController;
import com.suhayilmazok.controller.RestBaseController;
import com.suhayilmazok.controller.RootEntity;
import com.suhayilmazok.dto.DtoCustomer;
import com.suhayilmazok.dto.DtoCustomerIU;
import com.suhayilmazok.service.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/customer")
public class RestCustomerControllerImpl extends RestBaseController implements IRestCustomerController {

    @Autowired
    private ICustomerService customerService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoCustomer> saveCustomer(@Valid @RequestBody DtoCustomerIU dtoCustomerIU) {
        return ok(customerService.saveCustomer(dtoCustomerIU));
    }

    @GetMapping("/list/{id}")
    @Override
    public RootEntity<DtoCustomer> getCustomerByID(@PathVariable("id") Long id) {
        return ok(customerService.getCustomer(id));
    }

    @GetMapping("/list")
    @Override
    public RootEntity<List<DtoCustomer>> getAllCustomers() {
        return ok(customerService.getAllCustomers());
    }

    @DeleteMapping("delete/{id}")
    @Override
    public void deleteCustomer(@PathVariable(name = "id") Long id) {
         customerService.deleteCustomer(id);
    }

    @PutMapping("update/{id}")
    @Override
    public RootEntity<DtoCustomer> updateCustomer(@PathVariable(name = "id") Long id,@Valid @RequestBody DtoCustomerIU dtoCustomerIU) {

        return ok(customerService.updateCustomer(id, dtoCustomerIU));
    }
}
