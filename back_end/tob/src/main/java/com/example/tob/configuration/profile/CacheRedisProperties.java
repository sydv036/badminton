package com.example.tob.configuration.profile;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.cache.redis")
@Getter
@Setter
public class CacheRedisProperties {

    private long ttlMinutes;

    private String keyPrefixName;
}
