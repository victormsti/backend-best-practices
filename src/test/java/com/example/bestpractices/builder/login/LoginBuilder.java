package com.example.bestpractices.builder.login;

import com.example.bestpractices.dto.request.LoginRequest;
import com.example.bestpractices.dto.response.LoginResponse;

import java.util.UUID;

public class LoginBuilder {

    public static final LoginRequest buildLoginRequest(){
        return LoginRequest.builder()
                .email("email@gmail.com")
                .password("123456")
                .build();
    }

    public static final LoginResponse buildLoginResponse(){
        return LoginResponse.builder()
                .token(UUID.randomUUID().toString())
                .build();
    }
}
