package com.example.tob.exceptions;

import com.example.tob.common.global.dtos.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(value = {
            UsernameNotFoundException.class
    })
    public ResponseEntity<?> handleNotFoundException(Exception e) {
        CommonResponse<Object> response = new CommonResponse<>();
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        response.setMessage("Bad request");
        response.setErrorCode(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
