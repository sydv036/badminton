package com.example.tob.dtos.requests;

import com.example.tob.common.enums.GenderEnum;
import com.example.tob.constans.RegexConstans;
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

    @NotBlank(message = "{common.email.required}")
    @Email(message = "{common.email.invalid}")
    @UniqueEmailCustomize
    private String email;

    @NotBlank(message = "{common.phone.required}")
    @Pattern(regexp = RegexConstans.PHONE_VN, message = "{common.phone.invalid}")
    private String phoneNumber;

    @NotNull

    private GenderEnum gender;

    @NotBlank(message = "{common.password.required}")
    @Length(min = 8, message = "{common.password.invalid}")
    private String password;

}
