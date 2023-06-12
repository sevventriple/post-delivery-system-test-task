package com.example.postdeliverysystemtesttask.controller;

import com.example.postdeliverysystemtesttask.domain.dto.DeliveryEventDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Delivery Event Controller", description = "Контроллер для работы с событиями почтового отправления")
public interface DeliveryEventController {
    @Operation(
            summary = "создать новое событие",
            description = "Создать новое событие для почтового отправления"
    )
    ResponseEntity<DeliveryEventDto> createDeliveryEvent(DeliveryEventDto deliveryEventDto);

    @Operation(
            summary = "Получить события доставки",
            description = "Получить все события почтового отправления по id отправления"
    )
    List<DeliveryEventDto> getDeliveryEventsByDeliveryId(@Parameter(description = "Id почтового отправления") Long deliveryId);
}
