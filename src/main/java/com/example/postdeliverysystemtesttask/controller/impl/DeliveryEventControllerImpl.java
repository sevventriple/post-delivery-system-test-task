package com.example.postdeliverysystemtesttask.controller.impl;

import com.example.postdeliverysystemtesttask.controller.DeliveryEventController;
import com.example.postdeliverysystemtesttask.domain.dto.DeliveryEventDto;
import com.example.postdeliverysystemtesttask.domain.mapper.DeliveryEventMapper;
import com.example.postdeliverysystemtesttask.domain.model.DeliveryEvent;
import com.example.postdeliverysystemtesttask.service.DeliveryEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/delivery-events")
public class DeliveryEventControllerImpl implements DeliveryEventController {
    private final DeliveryEventService deliveryEventService;
    private final DeliveryEventMapper deliveryEventMapper;

    @Override
    @PostMapping
    public ResponseEntity<DeliveryEventDto> createDeliveryEvent(DeliveryEventDto inputEventDto) {
        DeliveryEvent deliveryEvent = deliveryEventService.createDeliveryEvent(inputEventDto);
        DeliveryEventDto deliveryEventDto = deliveryEventMapper.toDeliveryEventDto(deliveryEvent);
        return ResponseEntity
                .created(URI.create(String.format("/delivery-events/%d", deliveryEvent.getId())))
                .body(deliveryEventDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DeliveryEventDto> getDeliveryEventsByDeliveryId(@RequestParam("delivery-id") Long deliveryId) {
        return deliveryEventService.getDeliveryEventsByDeliveryId(deliveryId);
    }
}
