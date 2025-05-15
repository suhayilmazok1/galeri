package com.suhayilmazok.controller;

import com.suhayilmazok.dto.DtoAddress;
import com.suhayilmazok.dto.DtoAddressIU;

import java.util.List;

public interface IRestAddressController {

    public RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);

    public RootEntity<DtoAddress> findAddressById(Long id);

    public RootEntity<List<DtoAddress>> findAllAddresses();

    public void deleteAddress(Long id);

    public RootEntity<DtoAddress> updateAddress(Long id ,DtoAddressIU dtoAddressIU);

}
