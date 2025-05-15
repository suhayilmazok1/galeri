package com.suhayilmazok.controller.impl;

import com.suhayilmazok.controller.IRestAccountController;
import com.suhayilmazok.controller.RestBaseController;
import com.suhayilmazok.controller.RootEntity;
import com.suhayilmazok.dto.DtoAccount;
import com.suhayilmazok.dto.DtoAccountIU;
import com.suhayilmazok.dto.DtoAddressIU;
import com.suhayilmazok.service.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/account")
public class RestAccountControllerImpl extends RestBaseController implements IRestAccountController {

    @Autowired
    private IAccountService accountService;


    @PostMapping("/save")
    @Override
    public RootEntity<DtoAccount> saveAccount(@Valid @RequestBody DtoAccountIU dtoAccountIU) {
        return ok(accountService.saveAccount(dtoAccountIU));
    }

    @GetMapping("/list")
    @Override
    public RootEntity<List<DtoAccount>> getAllAccounts() {
        return ok(accountService.getAllAccounts());
    }

    @GetMapping("list/{id}")
    @Override
    public RootEntity<DtoAccount> getAccountById(@PathVariable(name = "id") Long id) {
        return ok(accountService.getAccountById(id));
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public void deleteAccount(@PathVariable(name = "id") Long id) {
        accountService.deleteAccount(id);
    }

    @PutMapping("/update/{id}")
    @Override
    public RootEntity<DtoAccount> updateAccount(@PathVariable(name = "id") Long id ,@Valid @RequestBody DtoAccountIU dtoAccountIU) {
        return ok(accountService.updateAccount(id, dtoAccountIU));
    }
}
