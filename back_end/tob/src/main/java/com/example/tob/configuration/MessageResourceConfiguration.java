package com.example.tob.configuration;

import com.example.tob.configuration.properties.WebProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageResourceConfiguration {

    private WebProperties webProperties;

    public MessageResourceConfiguration(WebProperties webProperties) {
        this.webProperties = webProperties;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(webProperties.getClassPathMessage());
        messageSource.setDefaultEncoding(webProperties.getEncoding());
        return messageSource;
    }
}
