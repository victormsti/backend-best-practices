package com.example.bestpractices.dto.response.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class UserResponse implements Serializable {

    private String id;
    private String name;
    private String role;
    private String email;
    private String password;
    private LocalDate birthDate;
    private LocalDate creationDate;
    private LocalDate updateDate;
}
