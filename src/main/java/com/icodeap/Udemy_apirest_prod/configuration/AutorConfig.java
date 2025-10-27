package com.icodeap.Udemy_apirest_prod.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;

@Configuration
public class AutorConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
