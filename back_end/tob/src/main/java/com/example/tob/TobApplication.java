package com.example.tob;

import com.example.tob.utils.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;


@SpringBootApplication
@ConfigurationPropertiesScan
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@RestController
@Slf4j
@RequiredArgsConstructor
public class TobApplication {

    private final MessageSource messageSource;

    private static final String MESTT001 = "MESTT001";

    public static void main(String[] args) {
        SpringApplication.run(TobApplication.class, args);
    }

    @GetMapping("/")
    @ApiMessage(value = "The first API Badminton")
    public String init() {
        return messageSource.getMessage(MESTT001, null, Locale.getDefault());

    }

}
