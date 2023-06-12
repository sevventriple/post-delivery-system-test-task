package com.example.postdeliverysystemtesttask.controller;

import com.example.postdeliverysystemtesttask.domain.dto.PostalDeliveryDto;
import com.example.postdeliverysystemtesttask.domain.dto.RegisterDeliveryEventDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Postal Delivery Controller", description = "Контроллер для работы с почтовыми отправлениями")
public interface PostalDeliveryController {
    @Operation(
            summary = "Создать отправление",
            description = "Создать новое почтовое отправление"
    )
    ResponseEntity<String> registerPostalDelivery(RegisterDeliveryEventDto registerDeliveryEventDto);

    @Operation(
            summary = "Получить почтовое отправление",
            description = "Получить почтовое отправление по Id"
    )
    PostalDeliveryDto getPostalDeliveryById(@Parameter(description = "Id почтового отправления") Long id);
}
