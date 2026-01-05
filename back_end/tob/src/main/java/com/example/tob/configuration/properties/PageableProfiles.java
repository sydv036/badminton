package com.example.tob.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.pageable")
@Getter
@Setter
public class PageableProfiles {

    private int maxPageSize;

    private boolean oneIndexPage;

}
