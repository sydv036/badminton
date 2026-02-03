package com.example.tob.services.auth.services;

import com.example.tob.configuration.properties.AuthProfiles;
import com.example.tob.configuration.properties.CacheRedisProperties;
import com.example.tob.services.auth.interfaces.ITokenBlacklistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenBlacklistServiceImpl implements ITokenBlacklistService {

    private final RedisTemplate<String, Object> redisTemplate;

    private final AuthProfiles authProfiles;

    private final CacheRedisProperties cacheRedisProperties;

    private static final String BLACKLIST_TOKEN_PREFIX = "blacklist:token:";

    private static final String VALUE_FIXED_BLACK_LIST= "blacklisted";

    /**
     * @param token
     */
    @Override
    public void blackListToken(String token) {
        String key = BLACKLIST_TOKEN_PREFIX + token;
        redisTemplate.opsForValue().set(key, VALUE_FIXED_BLACK_LIST, Duration.ofMillis(cacheRedisProperties.getTtlMillisSecond()));
    }

    /**
     * Handler to blacklist both access and refresh tokens
     *
     * @param accessToken
     * @param refreshToken
     */
    @Override
    public void blackListAllToken(String accessToken, String refreshToken) {
        String keyAccessToken = BLACKLIST_TOKEN_PREFIX + accessToken;
        String keyRefreshToken = BLACKLIST_TOKEN_PREFIX + refreshToken;
        redisTemplate.opsForValue().set(keyAccessToken, VALUE_FIXED_BLACK_LIST, Duration.ofMillis(authProfiles.getAccessTokenValidityInSeconds()));
        redisTemplate.opsForValue().set(keyRefreshToken, VALUE_FIXED_BLACK_LIST, Duration.ofMillis(authProfiles.getRefreshTokenValidityInSeconds()));
    }

    /**
     * Check token is blacklisted
     *
     * @param token
     * @return
     */
    @Override
    public boolean isTokenBlacklisted(String token) {
        String key = BLACKLIST_TOKEN_PREFIX + token;
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    /**
     * Remove token from blacklist
     *
     * @param token
     */
    @Override
    public void removeBlackListToken(String token) {
        String key = BLACKLIST_TOKEN_PREFIX + token;
        redisTemplate.delete(key);
    }

    /**
     * Get all blacklisted tokens
     *
     * @return
     */
    @Override
    public Set<String> getAllBlacklistedTokens() {
        Set<String> keys = redisTemplate.keys(BLACKLIST_TOKEN_PREFIX + "*");
        return keys;
    }
}
