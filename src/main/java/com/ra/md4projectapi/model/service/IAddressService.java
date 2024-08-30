package com.ra.md4projectapi.model.service;

import com.ra.md4projectapi.model.dto.request.AddressRequest;
import com.ra.md4projectapi.model.entity.Address;

import java.util.List;

public interface IAddressService {
    List<Address> findAll(Long userId);
    Address findById(Long id,Long userId);
    Address save(AddressRequest addressRequest,Long userId);
    void deleteById(Long id,Long userId);
}
