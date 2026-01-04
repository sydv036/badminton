package com.example.tob;

import com.example.tob.utils.annotation.ApiMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;


@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class
})
@ConfigurationPropertiesScan
@RestController
@Slf4j
public class TobApplication {

    @Autowired
    private MessageSource messageSource;

    private final String MESTT001 = "MESTT001";

    public static void main(String[] args) {
        SpringApplication.run(TobApplication.class, args);
    }

    @GetMapping("/")
    @ApiMessage(value = "The first API Badminton")
    public String init() {
        return messageSource.getMessage(MESTT001, null, Locale.getDefault());

    }

}
