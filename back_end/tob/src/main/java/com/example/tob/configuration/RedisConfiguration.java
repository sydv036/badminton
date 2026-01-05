package com.example.tob.configuration;

import com.example.tob.configuration.properties.CacheRedisProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * Config connection redis<br>
 * 1.Config default cache<br>
 * 2.Config redis template
 */
@Configuration
@EnableCaching
public class RedisConfiguration {

    private CacheRedisProperties cacheRedisProperties;

    public RedisConfiguration(CacheRedisProperties cacheRedisProperties) {
        this.cacheRedisProperties = cacheRedisProperties;
    }

    /**
     * Config default cache
     *
     * @param connectionFactory
     * @return redisCacheManager
     */
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        // Serializer for key (with String)
        RedisSerializationContext.SerializationPair<String> keySerialize =
                RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer());

        // Serializer for value (with JSON)
        RedisSerializationContext.SerializationPair<Object> valueSerialize =
                RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer());

        // Default config
        RedisCacheConfiguration cacheDefaultConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(cacheRedisProperties.getTtlMinutes()))   // Default TTL is 10 minutes
                .disableCachingNullValues()         // Not cache with value is null
                .serializeKeysWith(keySerialize)
                .serializeValuesWith(valueSerialize)
                .prefixCacheNameWith(cacheRedisProperties.getKeyPrefixName() + ":");

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(cacheDefaultConfig)
                .transactionAware()
                .build();
    }

    /**
     * Config redis template
     *
     * @param redisConnectionFactory
     * @return redisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // Serializer of key
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        //Serialize of value JSON
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
