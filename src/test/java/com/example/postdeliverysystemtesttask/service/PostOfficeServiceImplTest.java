package com.example.postdeliverysystemtesttask.service;

import com.example.postdeliverysystemtesttask.domain.exception.PostOfficeNotFoundException;
import com.example.postdeliverysystemtesttask.domain.model.PostOffice;
import com.example.postdeliverysystemtesttask.repository.PostOfficeRepository;
import com.example.postdeliverysystemtesttask.service.impl.PostOfficeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostOfficeServiceImplTest {
    @Mock
    private PostOfficeRepository postOfficeRepository;
    @InjectMocks
    private PostOfficeServiceImpl postOfficeService;

    @Test
    void getPostOfficeByPostCode() {
        //Given
        Integer existPostCode = 444000;
        Integer nonExistPostCode = 100000;
        PostOffice postOffice = new PostOffice();

        //When
        when(postOfficeRepository.findById(existPostCode)).thenReturn(Optional.of(postOffice));
        when(postOfficeRepository.findById(nonExistPostCode)).thenReturn(Optional.empty());

        //Then
        assertThat(postOfficeService.getPostOfficeByPostCode(existPostCode)).isEqualTo(postOffice);
        assertThatExceptionOfType(PostOfficeNotFoundException.class).isThrownBy(() -> postOfficeService.getPostOfficeByPostCode(nonExistPostCode));
    }
}