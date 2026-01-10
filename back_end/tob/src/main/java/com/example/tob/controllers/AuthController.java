package com.example.tob.controllers;

import com.example.tob.dtos.requests.LoginRequestDto;
import com.example.tob.dtos.requests.RegisterRequestDto;
import com.example.tob.dtos.responses.auth.LoginResponse;
import com.example.tob.services.auth.interfaces.IAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;

    @PostMapping("/register")
    public String handlerRegister(@RequestBody @Valid RegisterRequestDto registerRequestDto) {
        return authService.handlerRegister(registerRequestDto);
    }

    @PostMapping("/login")
    public LoginResponse handlerLogin(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        return authService.handlerLogin(loginRequestDto);
    }

}
