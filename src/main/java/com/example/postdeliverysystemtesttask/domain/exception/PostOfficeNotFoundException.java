package com.example.postdeliverysystemtesttask.domain.exception;

public class PostOfficeNotFoundException extends RuntimeException {
    public PostOfficeNotFoundException(Integer postCode) {
        super(String.format("Post office with postal code %d not found", postCode));
    }
}
