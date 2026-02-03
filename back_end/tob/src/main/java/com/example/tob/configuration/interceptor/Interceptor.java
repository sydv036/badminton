package com.example.tob.configuration.interceptor;

import com.example.tob.configuration.properties.AuthProfiles;
import com.example.tob.dtos.responses.auth.LoginResponse;
import com.example.tob.exceptions.AuthenticationException;
import com.example.tob.services.auth.interfaces.ITokenBlacklistService;
import com.example.tob.services.auth.services.UserDetailServiceCustomize;
import com.example.tob.utils.auth.AuthUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Locale;

@Component
@RequiredArgsConstructor
@Slf4j
public class Interceptor extends OncePerRequestFilter {

    private final AuthProfiles authProfiles;
    private final ITokenBlacklistService tokenBlacklistService;
    private final AuthUtils authUtils;
    private final MessageSource messageSource;

    private static final String MESE010 = "MESE010";


    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String accessToken = authUtils.getCookieValue(request, authProfiles.getAccessToken());

        if (StringUtils.isBlank(accessToken)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (tokenBlacklistService.isTokenBlacklisted(accessToken)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        try {
            if (StringUtils.isNotEmpty(accessToken)
                    && ObjectUtils.isNotEmpty(authUtils.validToken(accessToken))) {
                LoginResponse loginResponse = authUtils.getUserWithToken(request, authProfiles.getAccessToken());

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginResponse
                        , null
                        , loginResponse.getRoles().stream().map(role -> new SimpleGrantedAuthority(role)).toList());

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (AuthenticationException e) {
            log.error(messageSource.getMessage(MESE010, new String[]{e.getMessage()}, Locale.getDefault()));
        }

        filterChain.doFilter(request, response);

    }
}
