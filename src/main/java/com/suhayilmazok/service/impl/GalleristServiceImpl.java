package com.suhayilmazok.service.impl;

import com.suhayilmazok.dto.DtoAddress;
import com.suhayilmazok.dto.DtoGallerist;
import com.suhayilmazok.dto.DtoGalleristIU;
import com.suhayilmazok.exception.BaseException;
import com.suhayilmazok.exception.ErrorMessage;
import com.suhayilmazok.exception.MessageType;
import com.suhayilmazok.model.Address;
import com.suhayilmazok.model.Gallerist;
import com.suhayilmazok.repository.AddressRepository;
import com.suhayilmazok.repository.CustomerRepository;
import com.suhayilmazok.repository.GalleristRepository;
import com.suhayilmazok.service.IGalleristService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GalleristServiceImpl implements IGalleristService {

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CustomerRepository customerRepository;

    private Gallerist createGallerist(DtoGalleristIU dtoGalleristIU) {

        Optional<Address> optAddress = addressRepository.findById(dtoGalleristIU.getAddressId());
        if(optAddress.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristIU.getAddressId().toString()));
        }

        Gallerist gallerist = new Gallerist();
        gallerist.setCreateTime(new Date());

        BeanUtils.copyProperties(dtoGalleristIU, gallerist);
        gallerist.setAddress(optAddress.get());
        return gallerist;
    }

    @Override
    public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU) {
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoAddress dtoAddress = new DtoAddress();

        Gallerist savedGallerist = galleristRepository.save(createGallerist(dtoGalleristIU));


        BeanUtils.copyProperties(savedGallerist, dtoGallerist);
        BeanUtils.copyProperties(savedGallerist.getAddress(), dtoAddress);
        dtoGallerist.setAddress(dtoAddress);
        return dtoGallerist;
    }

    @Override
    public DtoGallerist getGallerist(Long id) {
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoAddress dtoAddress = new DtoAddress();

        Optional<Gallerist> optGallerist = galleristRepository.findById(id);
        if(optGallerist.isPresent()) {
            Gallerist gallerist = optGallerist.get();
            BeanUtils.copyProperties(gallerist, dtoGallerist);
            BeanUtils.copyProperties(gallerist.getAddress(), dtoAddress);


            dtoGallerist.setAddress(dtoAddress);
            return dtoGallerist;

        }
        return null;
    }

    @Override
    public List<DtoGallerist> getAllGallerist() {

        List<DtoGallerist> dtoGallerist = new ArrayList<>();
        List<Gallerist> gallerists = galleristRepository.findAll();

        for (Gallerist gallerist : gallerists) {
            DtoGallerist dto = new DtoGallerist();
            DtoAddress dtoAddress = new DtoAddress();
            BeanUtils.copyProperties(gallerist, dto);

            BeanUtils.copyProperties(gallerist.getAddress(), dtoAddress);
            dto.setAddress(dtoAddress);

            dtoGallerist.add(dto);
        }
        return dtoGallerist;
    }

    @Override
    public void deleteGallerist(Long id) {
        Optional<Gallerist> optGallerist = galleristRepository.findById(id);
        if(optGallerist.isPresent()) {
            Gallerist gallerist = optGallerist.get();
            galleristRepository.delete(gallerist);
        }
    }

    @Override
    public DtoGallerist updateGallerist(Long id, DtoGalleristIU dtoGalleristIU) {

        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoAddress dtoAddress = new DtoAddress();


        Optional<Gallerist> optGallerist = galleristRepository.findById(id);

        if(optGallerist.isPresent()) {

            Gallerist gallerist = optGallerist.get();

            Address address = addressRepository.findById(dtoGalleristIU.getAddressId()).orElse(null);

            // Address bilgilerini manuel olarak set et
            if (address != null) {
                dtoAddress.setCity(address.getCity());
                dtoAddress.setDistrict(address.getDistrict());
                dtoAddress.setStreet(address.getStreet());
                dtoAddress.setId(address.getId());
                dtoAddress.setNeighborhood(address.getNeighborhood());
                dtoAddress.setCreateTime(new Date());

                dtoGallerist.setAddress(dtoAddress);
            }

            gallerist.setAddress(address);
            gallerist.setCreateTime(new Date());
            gallerist.setFirstName(dtoGalleristIU.getFirstName());
            gallerist.setLastName(dtoGalleristIU.getLastName());

            galleristRepository.save(gallerist);

            BeanUtils.copyProperties(gallerist, dtoGallerist);
            return dtoGallerist;



        }
        return null;
    }
}
