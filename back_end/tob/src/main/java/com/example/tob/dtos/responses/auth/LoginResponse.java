package com.example.tob.dtos.responses.auth;


import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginResponse {

    private String publicId;

    private String email;

    private String phoneNumber;

    private Set<String> roles;

}
