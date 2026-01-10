package com.example.tob.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequestDto {

    @NotBlank(message = "{common.email.required}")
    @Email(message = "{common.email.invalid}")
    private String email;

    @NotBlank(message = "{common.password.required}")
    private String password;

}
