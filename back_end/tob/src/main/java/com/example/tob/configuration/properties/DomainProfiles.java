package com.example.tob.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.domain")
@Getter
@Setter
public class DomainProfiles {

    private String swaggerServer;

}
