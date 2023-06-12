package com.example.postdeliverysystemtesttask.domain.mapper;

import com.example.postdeliverysystemtesttask.domain.dto.PostalDeliveryDto;
import com.example.postdeliverysystemtesttask.domain.dto.RegisterDeliveryEventDto;
import com.example.postdeliverysystemtesttask.domain.model.Address;
import com.example.postdeliverysystemtesttask.domain.model.PostOffice;
import com.example.postdeliverysystemtesttask.domain.model.PostalDelivery;
import com.example.postdeliverysystemtesttask.service.AddressService;
import com.example.postdeliverysystemtesttask.service.PostOfficeService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PostalDeliveryMapper {
    @Autowired
    private AddressService addressService;
    @Autowired
    private PostOfficeService postOfficeService;

    @Mapping(source = "addresseeAddressId", target = "addresseeAddress", qualifiedByName = "setAddress")
    @Mapping(source = "targetPostalCode", target = "targetPostOffice", qualifiedByName = "setPostOffice")
    @Mapping(target = "deliveryStatus", constant = "IN_POST_OFFICE")
    public abstract PostalDelivery toPostalDelivery(RegisterDeliveryEventDto registerDeliveryEventDto);

    @Mapping(source = "addresseeAddress.id", target = "addressId")
    @Mapping(source = "targetPostOffice.postIndex", target = "postalCode")
    public abstract PostalDeliveryDto toPostalDeliveryDto(PostalDelivery postalDelivery);

    @Named("setAddress")
    protected Address setAddress(Long id) {
        return addressService.getAddressById(id);
    }

    @Named("setPostOffice")
    protected PostOffice setPostOffice(Integer postCode) {
        return postOfficeService.getPostOfficeByPostCode(postCode);
    }
}
