package com.example.postdeliverysystemtesttask.service;

import com.example.postdeliverysystemtesttask.domain.dto.DeliveryEventDto;
import com.example.postdeliverysystemtesttask.domain.dto.RegisterDeliveryEventDto;
import com.example.postdeliverysystemtesttask.domain.enumeration.DeliveryEventType;
import com.example.postdeliverysystemtesttask.domain.enumeration.DeliveryStatus;
import com.example.postdeliverysystemtesttask.domain.enumeration.PostalItemType;
import com.example.postdeliverysystemtesttask.domain.exception.PostalDeliveryNotFoundException;
import com.example.postdeliverysystemtesttask.domain.mapper.DeliveryEventMapper;
import com.example.postdeliverysystemtesttask.domain.model.DeliveryEvent;
import com.example.postdeliverysystemtesttask.domain.model.PostOffice;
import com.example.postdeliverysystemtesttask.domain.model.PostalDelivery;
import com.example.postdeliverysystemtesttask.repository.DeliveryEventRepository;
import com.example.postdeliverysystemtesttask.service.impl.DeliveryEventServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeliveryEventServiceImplTest {
    @Mock
    private DeliveryEventRepository deliveryEventRepository;
    @Mock
    private PostalDeliveryService postalDeliveryService;
    @Mock
    private DeliveryEventMapper deliveryEventMapper;
    @InjectMocks
    private DeliveryEventServiceImpl deliveryEventService;

    @Test
    void createRegisterDeliveryEvent() {
        //Given
        RegisterDeliveryEventDto registerDeliveryEventDto = getRegisterDeliveryEventDto();
        DeliveryEvent deliveryEvent = getDeliveryEvent();

        //When
        when(deliveryEventMapper.toDeliveryEvent(registerDeliveryEventDto)).thenReturn(deliveryEvent);
        when(deliveryEventRepository.save(deliveryEvent)).thenReturn(deliveryEvent);

        //Then
        assertThat(deliveryEventService.createRegisterDeliveryEvent(registerDeliveryEventDto)).isEqualTo(deliveryEvent);
    }

    @Test
    void createDeliveryEvent() {
        //Given
        DeliveryEventDto deliveryEventDto = getDeliverEventDto();
        DeliveryEvent deliveryEvent = getDeliveryEvent();
        DeliveryStatus exampleStatus = DeliveryStatus.ON_THE_WAY;

        //When
        when(deliveryEventMapper.toDeliveryEvent(deliveryEventDto)).thenReturn(deliveryEvent);
        when(deliveryEventMapper.toDeliveryStatus(any())).thenReturn(exampleStatus);
        when(postalDeliveryService.updateDeliveryStatus(any(), any())).thenReturn(new PostalDelivery());
        when(deliveryEventRepository.save(deliveryEvent)).thenReturn(deliveryEvent);

        //Then
        assertThat(deliveryEventService.createDeliveryEvent(deliveryEventDto)).isEqualTo(deliveryEvent);
    }

    @Test
    void getDeliveryEventsByDeliveryId() {
        //Given
        Long existId = 1L;
        Long nonExistId = 2L;
        DeliveryEvent deliveryEvent = getDeliveryEvent();
        List<DeliveryEvent> deliveryEventList = List.of(deliveryEvent);
        DeliveryEventDto deliverEventDto = getDeliverEventDto();
        List<DeliveryEventDto> deliveryEventDtoList = List.of(deliverEventDto);

        //When
        when(postalDeliveryService.isDeliveryExists(existId)).thenReturn(true);
        when(postalDeliveryService.isDeliveryExists(nonExistId)).thenReturn(false);
        when(deliveryEventRepository.getDeliveryEventsByDeliveryId(existId)).thenReturn(deliveryEventList);
        when(deliveryEventMapper.toDeliveryEventDto(deliveryEvent)).thenReturn(deliverEventDto);

        //Then
        assertThat(deliveryEventService.getDeliveryEventsByDeliveryId(existId)).isEqualTo(deliveryEventDtoList);
        assertThatExceptionOfType(PostalDeliveryNotFoundException.class)
                .isThrownBy(() -> deliveryEventService.getDeliveryEventsByDeliveryId(nonExistId));
    }

    DeliveryEvent getDeliveryEvent() {
        DeliveryEvent deliveryEvent = new DeliveryEvent();
        deliveryEvent.setDeliveryEventType(DeliveryEventType.RECEIVED_BY_POST_OFFICE);
        deliveryEvent.setPostalDelivery(new PostalDelivery());
        deliveryEvent.setId(1L);
        deliveryEvent.setPostOffice(new PostOffice());
        deliveryEvent.setTime(ZonedDateTime.now());

        return deliveryEvent;
    }

    DeliveryEventDto getDeliverEventDto() {
        DeliveryEventDto deliveryEventDto = new DeliveryEventDto();
        deliveryEventDto.setDeliveryEventType(DeliveryEventType.RECEIVED_BY_POST_OFFICE);
        deliveryEventDto.setPostalCode(443106);
        deliveryEventDto.setTime(ZonedDateTime.now());
        deliveryEventDto.setDeliveryId(1L);
        return deliveryEventDto;
    }

    RegisterDeliveryEventDto getRegisterDeliveryEventDto() {
        RegisterDeliveryEventDto registerDeliveryEventDto = new RegisterDeliveryEventDto();
        registerDeliveryEventDto.setTime(ZonedDateTime.now());
        registerDeliveryEventDto.setAddresseeName("Алексей");
        registerDeliveryEventDto.setAddresseeAddressId(2L);
        registerDeliveryEventDto.setPostalItemType(PostalItemType.LETTER);
        registerDeliveryEventDto.setTargetPostalCode(443106);
        registerDeliveryEventDto.setSourcePostalCode(101000);
        return registerDeliveryEventDto;
    }
}