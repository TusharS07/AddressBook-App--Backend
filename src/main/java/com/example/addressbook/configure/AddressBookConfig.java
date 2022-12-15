package com.example.addressbook.configure;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddressBookConfig {

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
