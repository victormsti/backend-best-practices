package com.example.bestpractices.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class UpdateUserRequest {

    private String name;
    private String role;
    private String email;
    private LocalDate birthDate;
}
