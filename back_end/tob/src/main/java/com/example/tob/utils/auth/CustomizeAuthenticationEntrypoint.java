package com.example.tob.utils.auth;

import com.example.tob.common.global.dtos.CommonResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Locale;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomizeAuthenticationEntrypoint implements AuthenticationEntryPoint {

    private final MessageSource messageSource;

    private final ObjectMapper objectMapper;

    private static final String MESE005 = "MESE005";

    /**
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        CommonResponse<Object> commonResponse = new CommonResponse<>();
        commonResponse.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        String errMessage = Optional.ofNullable(authException.getCause())
                .map(Throwable::getMessage)
                .orElse(authException.getMessage());
        commonResponse.setMessage(errMessage);
        commonResponse.setErrorCode(messageSource.getMessage(MESE005, null, Locale.getDefault()));
        objectMapper.writeValue(response.getWriter(), commonResponse);
    }
}
