package com.example.postdeliverysystemtesttask.service;

import com.example.postdeliverysystemtesttask.domain.dto.DeliveryEventDto;
import com.example.postdeliverysystemtesttask.domain.dto.RegisterDeliveryEventDto;
import com.example.postdeliverysystemtesttask.domain.model.DeliveryEvent;

import java.util.List;

public interface DeliveryEventService {
    DeliveryEvent createRegisterDeliveryEvent(RegisterDeliveryEventDto registerDeliveryEventDto);

    DeliveryEvent createDeliveryEvent(DeliveryEventDto deliveryEventDto);

    List<DeliveryEventDto> getDeliveryEventsByDeliveryId(Long deliveryId);
}
