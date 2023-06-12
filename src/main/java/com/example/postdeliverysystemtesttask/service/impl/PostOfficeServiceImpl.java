package com.example.postdeliverysystemtesttask.service.impl;

import com.example.postdeliverysystemtesttask.domain.exception.PostOfficeNotFoundException;
import com.example.postdeliverysystemtesttask.domain.model.PostOffice;
import com.example.postdeliverysystemtesttask.repository.PostOfficeRepository;
import com.example.postdeliverysystemtesttask.service.PostOfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostOfficeServiceImpl implements PostOfficeService {
    private final PostOfficeRepository postOfficeRepository;

    @Override
    public PostOffice getPostOfficeByPostCode(Integer postCode) {
        return postOfficeRepository.findById(postCode).orElseThrow(() -> new PostOfficeNotFoundException(postCode));
    }
}
