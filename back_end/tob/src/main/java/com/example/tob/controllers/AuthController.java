package com.example.tob.controllers;

import com.example.tob.dtos.requests.LoginRequestDto;
import com.example.tob.dtos.requests.RegisterRequestDto;
import com.example.tob.dtos.responses.auth.LoginResponse;
import com.example.tob.services.auth.interfaces.IAuthService;
import com.example.tob.utils.annotation.ApiMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;

    @PostMapping("/register")
    @ApiMessage(value = "Call API register")
    public String handlerRegister(@RequestBody @Valid RegisterRequestDto registerRequestDto) {
        return authService.handlerRegister(registerRequestDto);
    }

    @PostMapping("/login")
    @ApiMessage(value = "Call API login")
    public ResponseEntity<LoginResponse> handlerLogin(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        return authService.handlerLogin(loginRequestDto);
    }

    @GetMapping("/refresh-token")
    @ApiMessage(value = "Call API refresh token")
    public ResponseEntity<Object> handlerRefreshToken(HttpServletRequest request) {
        return authService.handlerRefreshToken(request);
    }

    @PostMapping("/logout")
    @ApiMessage(value = "Call API logout")
    public ResponseEntity<Object> handlerLogout(HttpServletResponse response, HttpServletRequest request) {
        return authService.handlerLogout(response, request);
    }

}
