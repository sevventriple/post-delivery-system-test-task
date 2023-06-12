package com.example.postdeliverysystemtesttask.service.impl;

import com.example.postdeliverysystemtesttask.domain.dto.DeliveryEventDto;
import com.example.postdeliverysystemtesttask.domain.dto.RegisterDeliveryEventDto;
import com.example.postdeliverysystemtesttask.domain.enumeration.DeliveryStatus;
import com.example.postdeliverysystemtesttask.domain.exception.PostalDeliveryNotFoundException;
import com.example.postdeliverysystemtesttask.domain.mapper.DeliveryEventMapper;
import com.example.postdeliverysystemtesttask.domain.model.DeliveryEvent;
import com.example.postdeliverysystemtesttask.repository.DeliveryEventRepository;
import com.example.postdeliverysystemtesttask.service.DeliveryEventService;
import com.example.postdeliverysystemtesttask.service.PostalDeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryEventServiceImpl implements DeliveryEventService {
    private final DeliveryEventRepository deliveryEventRepository;
    private final PostalDeliveryService postalDeliveryService;
    private final DeliveryEventMapper deliveryEventMapper;

    @Override
    @Transactional
    public DeliveryEvent createRegisterDeliveryEvent(RegisterDeliveryEventDto registerDeliveryEventDto) {
        DeliveryEvent deliveryEvent = deliveryEventMapper.toDeliveryEvent(registerDeliveryEventDto);
        return deliveryEventRepository.save(deliveryEvent);
    }

    @Override
    @Transactional
    public DeliveryEvent createDeliveryEvent(DeliveryEventDto deliveryEventDto) {
        DeliveryEvent deliveryEvent = deliveryEventMapper.toDeliveryEvent(deliveryEventDto);
        DeliveryStatus deliveryStatus = deliveryEventMapper.toDeliveryStatus(deliveryEventDto.getDeliveryEventType());
        postalDeliveryService.updateDeliveryStatus(deliveryEventDto.getDeliveryId(), deliveryStatus);
        return deliveryEventRepository.save(deliveryEvent);
    }

    @Override
    public List<DeliveryEventDto> getDeliveryEventsByDeliveryId(Long deliveryId) {
        if (!postalDeliveryService.isDeliveryExists(deliveryId)) {
            throw new PostalDeliveryNotFoundException(deliveryId);
        }
        return deliveryEventRepository.getDeliveryEventsByDeliveryId(deliveryId)
                .stream()
                .map(deliveryEventMapper::toDeliveryEventDto)
                .sorted(Comparator.comparing(DeliveryEventDto::getTime))
                .toList();
    }


}
