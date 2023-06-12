package com.example.postdeliverysystemtesttask.controller.impl;

import com.example.postdeliverysystemtesttask.controller.PostalDeliveryController;
import com.example.postdeliverysystemtesttask.domain.dto.PostalDeliveryDto;
import com.example.postdeliverysystemtesttask.domain.dto.RegisterDeliveryEventDto;
import com.example.postdeliverysystemtesttask.domain.mapper.PostalDeliveryMapper;
import com.example.postdeliverysystemtesttask.domain.model.DeliveryEvent;
import com.example.postdeliverysystemtesttask.domain.model.PostalDelivery;
import com.example.postdeliverysystemtesttask.service.DeliveryEventService;
import com.example.postdeliverysystemtesttask.service.PostalDeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/postal-deliveries")
public class PostalDeliveryControllerImpl implements PostalDeliveryController {
    private final DeliveryEventService deliveryEventService;
    private final PostalDeliveryService postalDeliveryService;
    private final PostalDeliveryMapper postalDeliveryMapper;

    @Override
    @PostMapping
    public ResponseEntity<String> registerPostalDelivery(RegisterDeliveryEventDto registerDeliveryEventDto) {
        DeliveryEvent registerDeliveryEvent = deliveryEventService.createRegisterDeliveryEvent(registerDeliveryEventDto);
        return ResponseEntity
                .created(URI.create(String.format("/postal-deliveries/%d", registerDeliveryEvent.getPostalDelivery().getId())))
                .body("delivery created");
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostalDeliveryDto getPostalDeliveryById(@PathVariable Long id) {
        PostalDelivery postalDeliveryById = postalDeliveryService.getPostalDeliveryById(id);
        return postalDeliveryMapper.toPostalDeliveryDto(postalDeliveryById);
    }
}
