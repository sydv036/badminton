package com.example.tob.configuration;

import com.example.tob.configuration.profile.RestTemplateProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfiguration {

    private RestTemplateProperties restTemplateProperties;

    public RestTemplateConfiguration(RestTemplateProperties restTemplateProperties) {
        this.restTemplateProperties = restTemplateProperties;
    }

    @Bean("restTemplate")
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .setConnectTimeout(Duration.ofMillis(restTemplateProperties.getConnectionTimeout()))
                .setReadTimeout(Duration.ofMillis(restTemplateProperties.getReadTimeout()))
                // When status code is 4xx, 5xx not throw
                .errorHandler(new ErrorHandlerCustomesize())
                .build();
    }
}
