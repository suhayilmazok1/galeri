package com.suhayilmazok.controller;

import com.suhayilmazok.dto.DtoCustomer;
import com.suhayilmazok.dto.DtoCustomerIU;

import java.util.List;

public interface IRestCustomerController {

    public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);

    public RootEntity<DtoCustomer> getCustomerByID(Long id);

    public RootEntity<List<DtoCustomer>> getAllCustomers();

    public void deleteCustomer(Long id);

    public RootEntity<DtoCustomer> updateCustomer(Long id ,DtoCustomerIU dtoCustomerIU);
}
