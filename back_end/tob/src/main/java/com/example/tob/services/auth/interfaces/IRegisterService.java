package com.example.tob.services.auth.interfaces;

import com.example.tob.dtos.requests.RegisterRequestDto;

public interface IRegisterService {

    /**
     * Handle register
     *
     * @param registerRequestDto info register
     * @return email of user registered
     */
    String handlerRegister(RegisterRequestDto registerRequestDto);

}
