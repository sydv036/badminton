package com.example.tob.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.rest")
@Getter
@Setter
public class RestTemplateProperties {

    private long connectionTimeout;

    private long readTimeout;

}
