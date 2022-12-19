package com.example.bestpractices.dto.response.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class UserOutboxResponse implements Serializable {

    private String id;
    private String email;
    private LocalDate creationDate;
    private LocalDate updateDate;
}
