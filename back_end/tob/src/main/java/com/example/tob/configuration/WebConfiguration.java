package com.example.tob.configuration;

import com.example.tob.configuration.profile.CorsProfiles;
import com.example.tob.configuration.profile.PageableProfiles;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final PageableProfiles pageableProfiles;

    private final CorsProfiles corsProfiles;

    public WebConfiguration(PageableProfiles pageableProfiles, CorsProfiles corsProfiles) {
        this.pageableProfiles = pageableProfiles;
        this.corsProfiles = corsProfiles;
    }

    /**
     * Config global format date time
     *
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        DateTimeFormatterRegistrar dateTimeFormatterRegistrar = new DateTimeFormatterRegistrar();
        // ISO 8601
        dateTimeFormatterRegistrar.setUseIsoFormat(true);
        dateTimeFormatterRegistrar.registerFormatters(registry);
    }

    /**
     * Config default pageable
     *
     * @param resolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        resolver.setOneIndexedParameters(pageableProfiles.isOneIndexPage());
        resolver.setMaxPageSize(pageableProfiles.getMaxPageSize());
        resolvers.add(resolver);
    }

    /**
     * Config global CORS
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(corsProfiles.getPathPattern())
                .allowedOrigins(corsProfiles.getAllowedOrigins())
                .allowedMethods(corsProfiles.getAllowedMethods())
                .allowedHeaders(corsProfiles.getAllowedHeaders())
                .allowCredentials(corsProfiles.getAllowedCredentials());
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().description("API for TOB Application")
                        .version("1.0")
                        .title("TOB API"));
    }

}
