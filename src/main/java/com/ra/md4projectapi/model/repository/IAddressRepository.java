package com.ra.md4projectapi.model.repository;

import com.ra.md4projectapi.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllByUserId(Long id);
}
