package com.clinic.doctor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author ahmed
 */


@Configuration
public class GlobalConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
