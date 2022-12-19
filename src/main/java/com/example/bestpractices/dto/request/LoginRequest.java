package com.example.bestpractices.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class LoginRequest {

    @NotNull(message = "email can't be null")
    private String email;

    @NotNull(message = "password can't be null")
    private String password;
}
