package com.example.postdeliverysystemtesttask.service;

import com.example.postdeliverysystemtesttask.domain.model.PostOffice;

public interface PostOfficeService {
    PostOffice getPostOfficeByPostCode(Integer postCode);
}
