package com.example.postdeliverysystemtesttask.domain.dto;

import com.example.postdeliverysystemtesttask.domain.enumeration.DeliveryEventType;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class DeliveryEventDto {
    private DeliveryEventType deliveryEventType;
    private ZonedDateTime time;
    private Integer postalCode;
    private Long deliveryId;
}
