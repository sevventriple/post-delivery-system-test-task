package com.example.postdeliverysystemtesttask.service;

import com.example.postdeliverysystemtesttask.domain.dto.RegisterDeliveryEventDto;
import com.example.postdeliverysystemtesttask.domain.enumeration.DeliveryStatus;
import com.example.postdeliverysystemtesttask.domain.exception.PostalDeliveryNotFoundException;
import com.example.postdeliverysystemtesttask.domain.mapper.PostalDeliveryMapper;
import com.example.postdeliverysystemtesttask.domain.model.PostalDelivery;
import com.example.postdeliverysystemtesttask.repository.PostalDeliveryRepository;
import com.example.postdeliverysystemtesttask.service.impl.PostalDeliveryServiceImpl;
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
class PostalDeliveryServiceImplTest {
    @Mock
    private PostalDeliveryRepository postalDeliveryRepository;
    @Mock
    private PostalDeliveryMapper postalDeliveryMapper;
    @InjectMocks
    private PostalDeliveryServiceImpl postalDeliveryService;

    @Test
    void getPostalDeliveryById() {
        //Given
        Long existId = 1L;
        Long nonExistId = 2L;
        PostalDelivery postalDelivery = new PostalDelivery();

        //When
        when(postalDeliveryRepository.findById(existId)).thenReturn(Optional.of(postalDelivery));
        when(postalDeliveryRepository.findById(nonExistId)).thenReturn(Optional.empty());

        //Then
        assertThat(postalDeliveryService.getPostalDeliveryById(existId)).isEqualTo(postalDelivery);
        assertThatExceptionOfType(PostalDeliveryNotFoundException.class)
                .isThrownBy(() -> postalDeliveryService.getPostalDeliveryById(nonExistId));
    }

    @Test
    void createAndSavePostalDelivery() {
        //Given
        RegisterDeliveryEventDto registerDeliveryEventDto = new RegisterDeliveryEventDto();
        PostalDelivery postalDelivery = new PostalDelivery();

        //When
        when(postalDeliveryMapper.toPostalDelivery(registerDeliveryEventDto)).thenReturn(postalDelivery);
        when(postalDeliveryRepository.save(postalDelivery)).thenReturn(postalDelivery);

        //Then
        assertThat(postalDeliveryService.createAndSavePostalDelivery(registerDeliveryEventDto)).isEqualTo(postalDelivery);
    }

    @Test
    void updateDeliveryStatus() {
        //Given
        Long existId = 1L;
        PostalDelivery postalDelivery = new PostalDelivery();

        //When
        when(postalDeliveryRepository.findById(existId)).thenReturn(Optional.of(postalDelivery));
        when(postalDeliveryRepository.save(postalDelivery)).thenReturn(postalDelivery);

        //Then
        assertThat(postalDeliveryService.updateDeliveryStatus(existId, DeliveryStatus.IN_POST_OFFICE).getDeliveryStatus())
                .isEqualTo(DeliveryStatus.IN_POST_OFFICE);
    }

    @Test
    void isDeliveryExists() {
        //Given
        Long existId = 1L;
        Long nonExistId = 2L;

        //When
        when(postalDeliveryRepository.existsById(existId)).thenReturn(true);
        when(postalDeliveryRepository.existsById(nonExistId)).thenReturn(false);

        //Then
        assertThat(postalDeliveryService.isDeliveryExists(existId)).isEqualTo(true);
        assertThat(postalDeliveryService.isDeliveryExists(nonExistId)).isEqualTo(false);
    }
}