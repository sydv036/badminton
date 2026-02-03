package com.example.tob.services.auth.interfaces;

import com.example.tob.dtos.requests.LoginRequestDto;
import com.example.tob.dtos.requests.RegisterRequestDto;
import com.example.tob.dtos.responses.auth.LoginResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

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
    ResponseEntity<LoginResponse> handlerLogin(LoginRequestDto loginRequestDto);

    /**
     * Handler refresh token when access token expire
     *
     * @param request
     * @return
     */
    ResponseEntity<Object> handlerRefreshToken(final HttpServletRequest request);

    /**
     * Handler logout
     *
     * @return
     */
    ResponseEntity<Object> handlerLogout(HttpServletResponse response, HttpServletRequest request);

}
