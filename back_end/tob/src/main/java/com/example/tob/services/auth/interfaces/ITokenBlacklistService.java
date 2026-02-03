package com.example.tob.services.auth.interfaces;

import java.util.Set;

public interface ITokenBlacklistService {

    void blackListToken(String token);

    /**
     * Handler to blacklist both access and refresh tokens
     *
     * @param accessToken
     * @param refreshToken
     */
    void blackListAllToken(String accessToken, String refreshToken);

    /**
     * Check token is blacklisted
     *
     * @param token
     * @return
     */
    boolean isTokenBlacklisted(String token);

    /**
     * Remove token from blacklist
     *
     * @param token
     */
    void removeBlackListToken(String token);

    /**
     * Get all blacklisted tokens
     *
     * @return
     */
    Set<String> getAllBlacklistedTokens();

}
