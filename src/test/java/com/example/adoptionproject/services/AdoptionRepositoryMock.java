package com.example.adoptionproject.services;

import com.example.adoptionproject.repositories.AdoptionRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;

public class AdoptionRepositoryMock {
    @Bean
    public AdoptionRepository adoptionRepository() {
        return Mockito.mock(AdoptionRepository.class);
    }

}
