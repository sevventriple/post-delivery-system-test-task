package com.example.postdeliverysystemtesttask.domain.mapper;

import com.example.postdeliverysystemtesttask.domain.dto.DeliveryEventDto;
import com.example.postdeliverysystemtesttask.domain.dto.RegisterDeliveryEventDto;
import com.example.postdeliverysystemtesttask.domain.enumeration.DeliveryEventType;
import com.example.postdeliverysystemtesttask.domain.enumeration.DeliveryStatus;
import com.example.postdeliverysystemtesttask.domain.model.DeliveryEvent;
import com.example.postdeliverysystemtesttask.domain.model.PostOffice;
import com.example.postdeliverysystemtesttask.domain.model.PostalDelivery;
import com.example.postdeliverysystemtesttask.service.PostOfficeService;
import com.example.postdeliverysystemtesttask.service.PostalDeliveryService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ValueMapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class DeliveryEventMapper {
    @Autowired
    private PostalDeliveryService postalDeliveryService;
    @Autowired
    private PostOfficeService postOfficeService;

    @Mapping(source = "postalCode", target = "postOffice", qualifiedByName = "setPostOffice")
    @Mapping(source = "deliveryId", target = "postalDelivery", qualifiedByName = "setExistingPostalDelivery")
    public abstract DeliveryEvent toDeliveryEvent(DeliveryEventDto deliveryEventDto);

    @Mapping(target = "deliveryEventType", constant = "RECEIVED_BY_POST_OFFICE")
    @Mapping(source = "sourcePostalCode", target = "postOffice", qualifiedByName = "setPostOffice")
    @Mapping(source = "registerDeliveryEventDto", target = "postalDelivery", qualifiedByName = "setNonExistentPostalDelivery")
    public abstract DeliveryEvent toDeliveryEvent(RegisterDeliveryEventDto registerDeliveryEventDto);

    @Mapping(source = "postOffice.postIndex", target = "postalCode")
    @Mapping(source = "postalDelivery.id", target = "deliveryId")
    public abstract DeliveryEventDto toDeliveryEventDto(DeliveryEvent deliveryEvent);

    @ValueMapping(source = "SENT_BY_POST_OFFICE", target = "ON_THE_WAY")
    @ValueMapping(source = "RECEIVED_BY_CUSTOMER", target = "RECEIVED_BY_CUSTOMER")
    @ValueMapping(source = "RECEIVED_BY_POST_OFFICE", target = "IN_POST_OFFICE")
    public abstract DeliveryStatus toDeliveryStatus(DeliveryEventType deliveryEventType);

    @Named("setPostOffice")
    protected PostOffice setPostOffice(Integer postalCode) {
        return postOfficeService.getPostOfficeByPostCode(postalCode);
    }

    @Named("setExistingPostalDelivery")
    protected PostalDelivery setExistingPostalDelivery(Long id) {
        return postalDeliveryService.getPostalDeliveryById(id);
    }

    @Named("setNonExistentPostalDelivery")
    protected PostalDelivery setNonExistentPostalDelivery(RegisterDeliveryEventDto registerDeliveryEventDto) {
        return postalDeliveryService.createAndSavePostalDelivery(registerDeliveryEventDto);
    }
}
