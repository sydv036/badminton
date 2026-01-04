package com.example.tob.configuration.profile;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.web")
@Getter
@Setter
public class WebProperties {

    private String encoding;

    private String classPathMessage;
}
