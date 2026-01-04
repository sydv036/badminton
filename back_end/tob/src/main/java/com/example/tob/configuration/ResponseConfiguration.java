package com.example.tob.configuration;


import com.example.tob.common.global.dtos.CommonResponse;
import com.example.tob.utils.annotation.ApiMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

/**
 * Configuration response global API
 */
@RestControllerAdvice
@Slf4j
public class ResponseConfiguration implements ResponseBodyAdvice<Object> {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MessageSource messageSource;

    private final String MESI001 = "MESI001";
    private final String MESTT002 = "MESTT002";
    private final String MESW001 = "MESW001";
    private final String MESI002 = "MESI002";

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();
        CommonResponse<Object> commonResponse = new CommonResponse<>();
        ApiMessage apiMessage = returnType.getMethodAnnotation(ApiMessage.class);

        int statusCode = servletResponse.getStatus();
        String mesage;

        // Check value annotation API
        if (ObjectUtils.isNotEmpty(apiMessage)) {
            mesage = apiMessage.value();
        } else {
            mesage = messageSource.getMessage(MESTT002, null, Locale.getDefault());
        }
        commonResponse.setStatusCode(statusCode);
        commonResponse.setMessage(mesage);
        commonResponse.setData(body);

        // Processing when response body is String
        if (body instanceof String) {
            try {
                response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                String objectConvertString = objectMapper.writeValueAsString(commonResponse);
                log.info(messageSource.getMessage(MESI002, new Object[]{objectConvertString}, Locale.getDefault()));
                return objectConvertString;
            } catch (Exception e) {
                log.warn(messageSource.getMessage(MESW001, new Object[]{e.getMessage()}, Locale.getDefault()));
            }
        }

        log.info(messageSource.getMessage(MESI001, new Object[]{commonResponse}, Locale.getDefault()));
        return commonResponse;
    }
}
