package com.example.postdeliverysystemtesttask.configuration;

import com.example.postdeliverysystemtesttask.domain.exception.AddressNotFoundException;
import com.example.postdeliverysystemtesttask.domain.exception.PostOfficeNotFoundException;
import com.example.postdeliverysystemtesttask.domain.exception.PostalDeliveryNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<String> handleAddressNotFoundException(AddressNotFoundException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(PostalDeliveryNotFoundException.class)
    public ResponseEntity<String> handlePostalDeliveryNotFoundException(PostalDeliveryNotFoundException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(PostOfficeNotFoundException.class)
    public ResponseEntity<String> handlePostOfficeNotFoundException(PostOfficeNotFoundException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
