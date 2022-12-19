package com.example.bestpractices.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class UserRequest {

    @NotNull(message = "User name can't be null")
    private String name;

    @NotNull(message = "User role can't be null")
    private String role;

    @NotNull(message = "User email can't be null")
    private String email;

    @NotNull(message = "User password can't be null")
    private String password;

    private LocalDate birthDate;
}
