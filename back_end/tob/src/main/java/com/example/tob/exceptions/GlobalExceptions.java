package com.example.tob.exceptions;

import com.example.tob.common.global.dtos.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptions {

    private final MessageSource messageSource;

    private static final String MESW002 = "MESW002";

    @ExceptionHandler(value = {
            UsernameNotFoundException.class
    })
    public ResponseEntity<Object> handleNotFoundException(Exception e) {
        CommonResponse<Object> response = new CommonResponse<>();
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        response.setMessage("Bad request");
        response.setErrorCode(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleMethodArgNotValid(MethodArgumentNotValidException e) {
        CommonResponse<Object> response = new CommonResponse<>();
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setErrorCode("Invalid");
        Map<String, List<String>> mapError = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(err ->
                mapError.computeIfAbsent(err.getField(), mess -> new ArrayList<>()).add(err.getDefaultMessage())
        );
        response.setMessage(mapError);

        log.warn(messageSource.getMessage(MESW002, new Object[]{mapError}, Locale.getDefault()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
