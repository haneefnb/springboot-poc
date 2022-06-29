package com.haneefs.springbootpoc.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haneefs.springbootpoc.utils.RequestHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class BeanConfig {

    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

}
