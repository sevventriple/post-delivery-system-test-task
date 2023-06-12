package com.example.postdeliverysystemtesttask.service.impl;

import com.example.postdeliverysystemtesttask.domain.dto.RegisterDeliveryEventDto;
import com.example.postdeliverysystemtesttask.domain.enumeration.DeliveryStatus;
import com.example.postdeliverysystemtesttask.domain.exception.PostalDeliveryNotFoundException;
import com.example.postdeliverysystemtesttask.domain.mapper.PostalDeliveryMapper;
import com.example.postdeliverysystemtesttask.domain.model.PostalDelivery;
import com.example.postdeliverysystemtesttask.repository.PostalDeliveryRepository;
import com.example.postdeliverysystemtesttask.service.PostalDeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostalDeliveryServiceImpl implements PostalDeliveryService {
    private final PostalDeliveryRepository postalDeliveryRepository;
    private final PostalDeliveryMapper postalDeliveryMapper;

    @Override
    public PostalDelivery getPostalDeliveryById(Long id) {
        return postalDeliveryRepository.findById(id).orElseThrow(() -> new PostalDeliveryNotFoundException(id));
    }

    @Override
    public PostalDelivery createAndSavePostalDelivery(RegisterDeliveryEventDto registerDeliveryEventDto) {
        PostalDelivery postalDelivery = postalDeliveryMapper.toPostalDelivery(registerDeliveryEventDto);
        postalDelivery.setDeliveryStatus(DeliveryStatus.IN_POST_OFFICE);
        return postalDeliveryRepository.save(postalDelivery);
    }

    @Override
    public PostalDelivery updateDeliveryStatus(Long id, DeliveryStatus deliveryStatus) {
        PostalDelivery postalDelivery = getPostalDeliveryById(id);
        postalDelivery.setDeliveryStatus(deliveryStatus);
        return postalDeliveryRepository.save(postalDelivery);
    }

    @Override
    public boolean isDeliveryExists(Long deliveryId) {
        return postalDeliveryRepository.existsById(deliveryId);
    }


}
