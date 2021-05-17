package com.github.tgiachi.jhamstudio.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SerializerBeanResolver {


    @Bean
    ObjectMapper getJsonObjectMapper() {
        return new ObjectMapper();
    }





}
