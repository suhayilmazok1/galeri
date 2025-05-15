package com.suhayilmazok.service;

import com.suhayilmazok.dto.DtoAddress;
import com.suhayilmazok.dto.DtoAddressIU;

import java.util.List;

public interface IAddressService {

    public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);

    public DtoAddress getAddressesByID(Long id);

    public List<DtoAddress> getAllAddresses();

    public void deleteAddress(Long id);

    public DtoAddress updateAddress(Long id ,DtoAddressIU dtoAddressIU);


}
