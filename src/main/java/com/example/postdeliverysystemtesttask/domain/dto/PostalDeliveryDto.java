package com.example.postdeliverysystemtesttask.domain.dto;

import com.example.postdeliverysystemtesttask.domain.enumeration.DeliveryStatus;
import com.example.postdeliverysystemtesttask.domain.enumeration.PostalItemType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostalDeliveryDto {
    private PostalItemType postalItemType;
    private DeliveryStatus deliveryStatus;
    private String addresseeName;
    private Long addressId;
    private Integer postalCode;
}
