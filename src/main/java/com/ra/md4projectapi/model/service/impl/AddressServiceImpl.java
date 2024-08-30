package com.ra.md4projectapi.model.service.impl;

import com.ra.md4projectapi.model.dto.request.AddressRequest;
import com.ra.md4projectapi.model.entity.Address;
import com.ra.md4projectapi.model.repository.IAddressRepository;
import com.ra.md4projectapi.model.service.IAddressService;
import com.ra.md4projectapi.model.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements IAddressService {
    private final IAddressRepository addressRepository;
    private final IUserService userService;
    // Get All Address
    @Override
    public List<Address> findAll(Long userId) {
        return addressRepository.findAllByUserId(userId);
    }

    // Get AddressById
    @Override
    public Address findById(Long id,Long userId) {
        Address address = addressRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Address not found"));
        if(!address.getUser().getId().equals(userId)){
            throw new NoSuchElementException("Address not found");
        }
        return address;
    }

    // Add New Address
    @Override
    public Address save(AddressRequest addressRequest, Long userId){
        Address address = null;
        address = Address.builder()
                .address(addressRequest.getAddress())
                .phone(addressRequest.getPhone())
                .receiveName(addressRequest.getReceiveName())
                .user(userService.getUserById(userId))
                .build();
        return addressRepository.save(address);
    }

    // Delete address by id
    @Override
    public void deleteById(Long id,Long userId) {
        Address address = addressRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Address not found"));
        if(!address.getUser().getId().equals(userId)){
            throw new NoSuchElementException("Address not found");
        }
        addressRepository.delete(address);
    }
}
