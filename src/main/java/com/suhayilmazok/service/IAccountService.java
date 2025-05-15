package com.suhayilmazok.service;

import com.suhayilmazok.dto.DtoAccount;
import com.suhayilmazok.dto.DtoAccountIU;
import com.suhayilmazok.dto.DtoAddressIU;

import java.util.List;

public interface IAccountService {

    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);

    public List<DtoAccount> getAllAccounts();

    public DtoAccount getAccountById(Long id);

    public void deleteAccount(Long id);

    public DtoAccount updateAccount(Long id ,DtoAccountIU dtoAccountIU);
}
