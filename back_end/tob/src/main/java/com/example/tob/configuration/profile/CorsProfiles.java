package com.example.tob.configuration.profile;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "app.cors")
@Getter
@Setter
public class CorsProfiles {

    private String pathPattern;

    private String allowedOrigins;

    private String allowedMethods;

    private String allowedHeaders;

    private Boolean allowedCredentials;

}
