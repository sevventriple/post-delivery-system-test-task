package com.example.postdeliverysystemtesttask.service;

import com.example.postdeliverysystemtesttask.domain.exception.AddressNotFoundException;
import com.example.postdeliverysystemtesttask.domain.model.Address;
import com.example.postdeliverysystemtesttask.repository.AddressRepository;
import com.example.postdeliverysystemtesttask.service.impl.AddressServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {
    @Mock
    private AddressRepository addressRepository;
    @InjectMocks
    private AddressServiceImpl addressService;

    @Test
    void getAddressById() {
        //Given
        Long existId = 1L;
        Long nonExistId = 2L;
        Address address = new Address();

        //When
        when(addressRepository.findById(existId)).thenReturn(Optional.of(address));
        when(addressRepository.findById(nonExistId)).thenReturn(Optional.empty());

        //Then
        assertThat(addressService.getAddressById(existId)).isEqualTo(address);
        assertThatExceptionOfType(AddressNotFoundException.class)
                .isThrownBy(() -> addressService.getAddressById(nonExistId));
    }
}