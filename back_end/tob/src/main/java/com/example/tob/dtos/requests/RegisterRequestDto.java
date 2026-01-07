package com.example.tob.dtos.requests;

import com.example.tob.common.enums.GenderEnum;
import com.example.tob.helpers.annotations.UniqueEmailCustomize;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

/**
 * Member request dto
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterRequestDto {

    @NotBlank(message = "Email is required")
    @Email(message = "Email is not valid")
    @UniqueEmailCustomize
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern( regexp = "(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})", message = "Phone number is not valid")
    private String phoneNumber;

    @NotNull

    private GenderEnum gender;

    @NotBlank(message = "Password number is required")
    @Length(min = 8, message = "Password must be at least 8 characters!")
    private String password;

}
