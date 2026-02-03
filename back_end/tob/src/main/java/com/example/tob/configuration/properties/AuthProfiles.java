package com.example.tob.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.auth.jwt")
@Getter
@Setter
public class AuthProfiles {

    private String base64Secret;

    private Long accessTokenValidityInSeconds;

    private Long refreshTokenValidityInSeconds;

    private String claimUser;

    private String claimAuthority;

    private String accessToken;

    private String refreshToken;

    private boolean cookieHttpOnly;

    private boolean cookieSecure;

    private String cookiePath;

    private Integer cookieMaxAgeAccessToken;

    private Integer cookieMaxAgeRefreshToken;

}
