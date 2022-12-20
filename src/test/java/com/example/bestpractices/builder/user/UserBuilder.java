package com.example.bestpractices.builder.user;

import com.example.bestpractices.dto.request.UserRequest;
import com.example.bestpractices.dto.response.user.UserOutboxResponse;
import com.example.bestpractices.dto.response.user.UserResponse;
import com.example.bestpractices.model.user.User;
import com.example.bestpractices.model.user.UserOutbox;

import java.time.LocalDate;
import java.util.UUID;

public class UserBuilder {

    public static final User buildUser(){
        return User.builder()
                .name("name")
                .email("email@gmail.com")
                .password("$2a$12$nYyKsX7phh9JuPdktM2kcOkmrdAjBPjS8S7XlTfD8nioq3uKQNVK2")
                .role("Software Engineer")
                .birthDate(LocalDate.now())
                .creationDate(LocalDate.now())
                .updateDate(LocalDate.now())
                .build();
    }

    public static final User buildSecondUser(){
        return User.builder()
                .name("nameTwo")
                .email("emailtwo@gmail.com")
                .password("$2a$12$nYyKsX7phh9JuPdktM2kcOkmrdAjBPjS8S7XlTfD8nioq3uKQNVK2")
                .role("Senior Software Engineer")
                .birthDate(LocalDate.now())
                .creationDate(LocalDate.now())
                .updateDate(LocalDate.now())
                .build();
    }

    public static final UserRequest buildUserRequest(){
        return UserRequest.builder()
                .name("name")
                .email("email@gmail.com")
                .password("123456")
                .role("Software Engineer")
                .build();
    }

    public static final UserRequest buildSecondUserRequest(){
        return UserRequest.builder()
                .name("nameTwo")
                .email("emailtwo@gmail.com")
                .password("1234567")
                .role("Senior Software Engineer")
                .build();
    }

    public static final UserResponse buildUserResponse(){
        return UserResponse.builder()
                .id(UUID.randomUUID().toString())
                .name("name")
                .email("email@gmail.com")
                .role("Software Engineer")
                .birthDate(LocalDate.now())
                .creationDate(LocalDate.now())
                .updateDate(LocalDate.now())
                .build();
    }

    public static final UserOutbox buildUserOutbox(){
        return UserOutbox.builder()
                .id(UUID.randomUUID().toString())
                .email("email@gmail.com")
                .creationDate(LocalDate.now())
                .updateDate(LocalDate.now())
                .build();
    }

    public static final UserOutboxResponse buildUserOutboxResponse(){
        return UserOutboxResponse.builder()
                .id(UUID.randomUUID().toString())
                .email("email@gmail.com")
                .creationDate(LocalDate.now())
                .updateDate(LocalDate.now())
                .build();
    }
}
