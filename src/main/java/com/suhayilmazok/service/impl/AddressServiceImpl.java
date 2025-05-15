package com.suhayilmazok.service.impl;

import com.suhayilmazok.dto.DtoAddress;
import com.suhayilmazok.dto.DtoAddressIU;
import com.suhayilmazok.exception.BaseException;
import com.suhayilmazok.exception.ErrorMessage;
import com.suhayilmazok.exception.MessageType;
import com.suhayilmazok.model.Address;
import com.suhayilmazok.repository.AddressRepository;
import com.suhayilmazok.service.IAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    private Address createAddress(DtoAddressIU dtoAddressIU) {
        Address address = new Address();
        address.setCreateTime(new Date());

        BeanUtils.copyProperties(dtoAddressIU, address);
        return address;
    }


    @Override
    public DtoAddress saveAddress(DtoAddressIU dtoAddressIU) {
        DtoAddress dtoAddress = new DtoAddress();

        Address savedAddress = addressRepository.save(createAddress(dtoAddressIU));
        BeanUtils.copyProperties(savedAddress, dtoAddress);
        return dtoAddress;
    }

    @Override
    public DtoAddress getAddressesByID(Long id) {
        DtoAddress dtoAddress = new DtoAddress();

        Optional<Address> optional = addressRepository.findById(id);
        if (optional.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Address not found"));
        }
        Address address = optional.get();
        BeanUtils.copyProperties(address, dtoAddress);
        return dtoAddress;
    }

    @Override
    public List<DtoAddress> getAllAddresses() {

        List<DtoAddress> dtoList = new ArrayList<>();

        List<Address> addresses = addressRepository.findAll();

        for (Address dbAddress : addresses) {
            DtoAddress dtoAddress = new DtoAddress();
            BeanUtils.copyProperties(dbAddress, dtoAddress);
            dtoList.add(dtoAddress);
        }
        return dtoList;
    }

    @Override
    public void deleteAddress(Long id) {
        Optional<Address> optional = addressRepository.findById(id);
        if(optional.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Address not found"));
        } else {
            addressRepository.delete(optional.get());
        }
    }

    @Override
    public DtoAddress updateAddress(Long id ,DtoAddressIU dtoAddressIU) {
        DtoAddress dtoAddress = new DtoAddress();
        Optional<Address> optional = addressRepository.findById(id);
        if (optional.isPresent()) {

            Address address = optional.get();

            address.setCity(dtoAddressIU.getCity());
            address.setDistrict(dtoAddressIU.getDistrict());
            address.setStreet(dtoAddressIU.getStreet());
            address.setNeighborhood(dtoAddressIU.getNeighborhood());

            Address uptatedAddress = addressRepository.save(address);

            BeanUtils.copyProperties(uptatedAddress, dtoAddress);
        }

        return dtoAddress;
    }


}
