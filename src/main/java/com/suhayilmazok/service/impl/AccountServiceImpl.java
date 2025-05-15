package com.suhayilmazok.service.impl;

import com.suhayilmazok.dto.DtoAccount;
import com.suhayilmazok.dto.DtoAccountIU;
import com.suhayilmazok.dto.DtoAddressIU;
import com.suhayilmazok.exception.BaseException;
import com.suhayilmazok.exception.ErrorMessage;
import com.suhayilmazok.model.Account;
import com.suhayilmazok.repository.AccountRepository;
import com.suhayilmazok.service.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    private Account createAccount(DtoAccountIU dtoAccountIU) {

        Account account = new Account();
        account.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoAccountIU, account);
        return account;
    }


    @Override
    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU) {
        DtoAccount dtoAccount = new DtoAccount();

        Account savedAccount = accountRepository.save(createAccount(dtoAccountIU));
        BeanUtils.copyProperties(savedAccount, dtoAccount);
        return dtoAccount;
    }

    @Override
    public List<DtoAccount> getAllAccounts() {

        List<Account> accounts = accountRepository.findAll();

        List<DtoAccount> dtoAccounts = new ArrayList<>();

        for (Account account : accounts) {
            DtoAccount dtoAccount = new DtoAccount();
            BeanUtils.copyProperties(account, dtoAccount);
            dtoAccounts.add(dtoAccount);
        }

        return dtoAccounts;
    }

    @Override
    public DtoAccount getAccountById(Long id) {
        DtoAccount dtoAccount = new DtoAccount();
        Optional<Account> getAccountByID = accountRepository.findById(id);
        if (getAccountByID.isPresent()) {
            Account account = getAccountByID.get();
            BeanUtils.copyProperties(account, dtoAccount);
        }
        return dtoAccount;

    }

    @Override
    public void deleteAccount(Long id) {
       Optional<Account> getAccountByID = accountRepository.findById(id);
       if (getAccountByID.isPresent()) {
           accountRepository.delete(getAccountByID.get());
       }
    }

    @Override
    public DtoAccount updateAccount(Long id ,DtoAccountIU dtoAccountIU) {
        DtoAccount dtoAccount = new DtoAccount();

        Optional<Account> optional = accountRepository.findById(id);
        if (optional.isPresent()) {
            Account account = optional.get();

            account.setAccountNo(dtoAccountIU.getAccountNo());
            account.setIban(dtoAccountIU.getIban());
            account.setCreateTime(new Date());
            account.setCurrencyType(dtoAccountIU.getCurrencyType());
            account.setAmount(dtoAccountIU.getAmount());

            Account updatedAccount = accountRepository.save(account);
            BeanUtils.copyProperties(updatedAccount, dtoAccount);
            return dtoAccount;
        }
        return null;
    }
}
