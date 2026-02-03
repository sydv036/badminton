package com.example.tob.utils.auth;

import com.example.tob.configuration.properties.AuthProfiles;
import com.example.tob.dtos.responses.auth.LoginResponse;
import com.example.tob.services.auth.interfaces.ITokenBlacklistService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import java.util.*;
import java.util.function.Consumer;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthUtils {

    private final AuthProfiles authProfiles;

    private final ITokenBlacklistService tokenBlacklistService;

    private final PasswordEncoder passwordEncoder;

    private final JwtEncoder jwtEncoder;

    private final JwtDecoder jwtDecoder;

    private final MessageSource messageSource;

    private final Instant now = Instant.now();

    private static final String MESE004 = "MESE004";
    private static final String MESE007 = "MESE007";

    private static final MacAlgorithm JWT_ALGORITHM = MacAlgorithm.HS256;


    private Map<String, String> userInfo(LoginResponse loginResponse) {
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("publicId", String.valueOf(loginResponse.getPublicId()));
        userInfo.put("email", loginResponse.getEmail());
        userInfo.put("phoneNumber", loginResponse.getPhoneNumber());
        return userInfo;
    }

    public String createAccessToken(LoginResponse loginResponse) {
        Instant validity = now.plus(authProfiles.getAccessTokenValidityInSeconds(), ChronoUnit.SECONDS);

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(validity)
                .subject(loginResponse.getEmail())
                .claims(claimsUserInfo(loginResponse))
                .build();

        JwsHeader jwsHeader = JwsHeader.with(JWT_ALGORITHM).build();
        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, jwtClaimsSet)).getTokenValue();
    }

    public String createRefreshToken(LoginResponse loginResponse) {
        Instant validity = now.plus(authProfiles.getRefreshTokenValidityInSeconds(), ChronoUnit.SECONDS);

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(validity)
                .subject(loginResponse.getEmail())
                .claims(claimsUserInfo(loginResponse))
                .build();

        JwsHeader jwsHeader = JwsHeader.with(JWT_ALGORITHM).build();
        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, jwtClaimsSet)).getTokenValue();
    }

    private Consumer<Map<String, Object>> claimsUserInfo(LoginResponse loginResponse) {
        Set<String> listAuthority = loginResponse.getRoles();
        return claim -> {
            claim.put(authProfiles.getClaimUser(), userInfo(loginResponse));
            claim.put(authProfiles.getClaimAuthority(), listAuthority);
        };
    }

    public Jwt validToken(String token) {
        try {
            if (tokenBlacklistService.isTokenBlacklisted(token)) return null;

            return jwtDecoder.decode(token);
        } catch (AuthenticationException e) {
            log.error(messageSource.getMessage(MESE004, new String[]{e.getMessage()}, Locale.getDefault()));
            throw e;
        }
    }

    public String getCookieValue(HttpServletRequest request, final String cookieName) {
        Cookie[] cookies = request.getCookies();

        if (ObjectUtils.isNotEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }

    public String cookieSetting(String tokenType, String tokenValue, Integer expirator) {
        ResponseCookie cookie = ResponseCookie
                .from(tokenType, tokenValue)
                .httpOnly(authProfiles.isCookieHttpOnly())
                .secure(authProfiles.isCookieSecure())
                .maxAge(expirator)
                .path(authProfiles.getCookiePath())
                .build();
        return cookie.toString();
    }

    public void clearCookieSetting(HttpServletResponse response) {
        ResponseCookie accessCookie = ResponseCookie
                .from(authProfiles.getAccessToken(), StringUtils.EMPTY)
                .maxAge(0)
                .secure(authProfiles.isCookieSecure())
                .httpOnly(authProfiles.isCookieHttpOnly())
                .path(authProfiles.getCookiePath())
                .build();

        ResponseCookie refreshCookie = ResponseCookie
                .from(authProfiles.getRefreshToken(), StringUtils.EMPTY)
                .maxAge(0)
                .secure(authProfiles.isCookieSecure())
                .httpOnly(authProfiles.isCookieHttpOnly())
                .path(authProfiles.getCookiePath())
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, accessCookie.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());
    }

    public LoginResponse getUserWithToken(final HttpServletRequest request, String cookieName) {

        String token = getCookieValue(request, cookieName);

        if (StringUtils.isBlank(token)) {
            throw new com.example.tob.exceptions.AuthenticationException(messageSource.getMessage(MESE007, null, Locale.getDefault()));
        }

        if (tokenBlacklistService.isTokenBlacklisted(token)) return null;

        Jwt jwt = validToken(token);

        if (ObjectUtils.isEmpty(jwt)) {
            throw new com.example.tob.exceptions.AuthenticationException(messageSource.getMessage(MESE007, null, Locale.getDefault()));
        }

        Map<String, Object> userMap = jwt.getClaimAsMap(authProfiles.getClaimUser());

        List<String> roles = jwt.getClaimAsStringList(authProfiles.getClaimAuthority());

        return LoginResponse.builder()
                .publicId(String.valueOf(userMap.get("publicId").toString()))
                .email((String) userMap.get("email"))
                .phoneNumber((String) userMap.get("phoneNumber"))
                .roles(new HashSet<>(roles))
                .build();

    }

}
