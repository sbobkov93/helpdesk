package com.example.helpdesk;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class HelpdeskConfiguration {

    private final List<TypeMapConfigurator> configurators;

    @Autowired
    public HelpdeskConfiguration(List<TypeMapConfigurator> configurators) {
        this.configurators = configurators;
    }

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        configurators.forEach(c -> c.setUp(modelMapper));
        return modelMapper;
    }

}
