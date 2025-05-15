package com.suhayilmazok.service;

import com.suhayilmazok.dto.DtoCustomer;
import com.suhayilmazok.dto.DtoCustomerIU;

import java.util.List;

public interface ICustomerService {

    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);

    public DtoCustomer getCustomer(Long id);

    public List<DtoCustomer> getAllCustomers();

    public void deleteCustomer(Long id);

    public DtoCustomer updateCustomer(Long id, DtoCustomerIU dtoCustomerIU);
}
