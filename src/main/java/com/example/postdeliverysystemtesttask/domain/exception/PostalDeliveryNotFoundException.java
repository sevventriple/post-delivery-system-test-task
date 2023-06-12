package com.example.postdeliverysystemtesttask.domain.exception;

public class PostalDeliveryNotFoundException extends RuntimeException {
    public PostalDeliveryNotFoundException(Long id) {
        super(String.format("Postal delivery with id %d not found", id));
    }
}
