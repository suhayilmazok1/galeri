package com.suhayilmazok.repository;

import com.suhayilmazok.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> id(Long id);
}
