package com.springboot.webautomation.services;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class FakerService {


    @Bean
    public Faker getFaker() {
        return new Faker();
    }
}
