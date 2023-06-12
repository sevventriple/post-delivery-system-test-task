package com.example.postdeliverysystemtesttask.service.impl;

import com.example.postdeliverysystemtesttask.domain.exception.AddressNotFoundException;
import com.example.postdeliverysystemtesttask.domain.model.Address;
import com.example.postdeliverysystemtesttask.repository.AddressRepository;
import com.example.postdeliverysystemtesttask.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Override
    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException(id));
    }
}
