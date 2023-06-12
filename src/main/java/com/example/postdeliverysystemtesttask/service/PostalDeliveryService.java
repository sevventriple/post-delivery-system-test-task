package com.example.postdeliverysystemtesttask.service;

import com.example.postdeliverysystemtesttask.domain.dto.RegisterDeliveryEventDto;
import com.example.postdeliverysystemtesttask.domain.enumeration.DeliveryStatus;
import com.example.postdeliverysystemtesttask.domain.model.PostalDelivery;

public interface PostalDeliveryService {
    PostalDelivery getPostalDeliveryById(Long id);

    PostalDelivery createAndSavePostalDelivery(RegisterDeliveryEventDto registerDeliveryEventDto);

    PostalDelivery updateDeliveryStatus(Long id, DeliveryStatus deliveryStatus);

    boolean isDeliveryExists(Long deliveryId);
}
