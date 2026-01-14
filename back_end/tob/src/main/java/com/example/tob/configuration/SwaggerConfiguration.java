package com.example.tob.configuration;

import com.example.tob.configuration.properties.DomainProfiles;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfiguration {

    private final DomainProfiles domainProfiles;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().description("API for TOB Application")
                        .version("1.0")
                        .title("TOB API"))
                .servers(Arrays.stream(domainProfiles.getSwaggerServer().split(",")).map(
                        serverUrl -> new Server().url(serverUrl.trim())
                ).toList());
    }

}
