package com.example.tob.common.global.dtos;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Configuration common body response global API
 *
 * @param <T> Object response client
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommonResponse<T> {
    private int statusCode;
    private Object message;
    private T data;
    private String errorCode;
    private LocalDateTime timestamp = LocalDateTime.now();
}
