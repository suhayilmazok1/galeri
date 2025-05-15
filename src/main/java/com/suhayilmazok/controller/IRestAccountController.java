package com.suhayilmazok.controller;

import com.suhayilmazok.dto.DtoAccount;
import com.suhayilmazok.dto.DtoAccountIU;
import com.suhayilmazok.dto.DtoAddressIU;

import java.util.List;

public interface IRestAccountController {

    public RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);

    public RootEntity<List<DtoAccount>> getAllAccounts();

    public RootEntity<DtoAccount> getAccountById(Long id);

    public void deleteAccount(Long id);

    public RootEntity<DtoAccount> updateAccount(Long id ,DtoAccountIU dtoAccountIU);
}
