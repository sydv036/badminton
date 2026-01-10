package com.example.tob.services.auth.interfaces;

import com.example.tob.dtos.requests.LoginRequestDto;
import com.example.tob.dtos.requests.RegisterRequestDto;
import com.example.tob.dtos.responses.auth.LoginResponse;

public interface IAuthService {

    /**
     * Handle register
     *
     * @param registerRequestDto info register
     * @return email of user registered
     */
    String handlerRegister(RegisterRequestDto registerRequestDto);


    /**
     * Handle login
     *
     * @param loginRequestDto info login
     * @return MemberInfoResponse contain user info
     */
    LoginResponse handlerLogin(LoginRequestDto loginRequestDto);

}
