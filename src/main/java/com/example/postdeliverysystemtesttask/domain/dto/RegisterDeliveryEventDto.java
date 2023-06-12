package com.example.postdeliverysystemtesttask.domain.dto;

import com.example.postdeliverysystemtesttask.domain.enumeration.PostalItemType;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class RegisterDeliveryEventDto {
    private ZonedDateTime time;
    private Integer sourcePostalCode;
    private Integer targetPostalCode;
    private String addresseeName;
    private Long addresseeAddressId;
    private PostalItemType postalItemType;
}
