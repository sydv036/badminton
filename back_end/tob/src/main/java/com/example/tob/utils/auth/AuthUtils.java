package com.example.tob.utils.auth;

import com.example.tob.configuration.properties.AuthProfiles;
import com.example.tob.dtos.responses.auth.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthUtils {

    private final AuthProfiles authProfiles;

    private final PasswordEncoder passwordEncoder;

    private final JwtEncoder jwtEncoder;

    private final JwtDecoder jwtDecoder;

    private final MessageSource messageSource;

    private final Instant now = Instant.now();

    private final String MESE004 = "MESE004";

    private static final MacAlgorithm JWT_ALGORITHM = MacAlgorithm.HS256;


    private Map<String, String> userInfo(LoginResponse loginResponse) {
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("systemId", String.valueOf(loginResponse.getSystemId()));
        userInfo.put("email", loginResponse.getEmail());
        userInfo.put("phoneNumber", loginResponse.getPhoneNumber());
        return userInfo;
    }

    public String createAccessToken(LoginResponse loginResponse) {
        Instant validity = now.plus(authProfiles.getAccessTokenValidityInSeconds(), ChronoUnit.SECONDS);
        Set<String> listAuthority = loginResponse.getRoles();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(validity)
                .subject(loginResponse.getEmail())
                .claim(authProfiles.getClaimUser(), userInfo(loginResponse))
                .claim(authProfiles.getClaimAuthority(), listAuthority)
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
                .claim(authProfiles.getClaimUser(), userInfo(loginResponse))
                .build();

        JwsHeader jwsHeader = JwsHeader.with(JWT_ALGORITHM).build();
        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, jwtClaimsSet)).getTokenValue();
    }

    public Jwt validRefreshToken(String refreshToken) {
        try {
            return jwtDecoder.decode(refreshToken);
        } catch (AuthenticationException e) {
            log.error(messageSource.getMessage(MESE004, new String[]{e.getMessage()}, Locale.getDefault()));
            throw e;
        }
    }

}
