package com.clinic.admin.config;

import com.clinic.admin.exceptions.RestTemplateResponseErrorHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Ahmed
 */


@Configuration
public class GlobalConfig {

    @Bean
    public RestTemplate restTemplate(){
        return   new RestTemplateBuilder().errorHandler(new RestTemplateResponseErrorHandler()).build();
    }

}
