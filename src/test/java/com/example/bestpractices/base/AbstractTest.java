package com.example.bestpractices.base;

import com.example.bestpractices.builder.login.LoginBuilder;
import com.example.bestpractices.builder.user.UserBuilder;
import com.example.bestpractices.dto.request.LoginRequest;
import com.example.bestpractices.dto.request.UserRequest;
import com.example.bestpractices.dto.response.LoginResponse;
import com.example.bestpractices.dto.response.user.UserResponse;
import com.example.bestpractices.model.user.User;
import com.example.bestpractices.model.user.UserOutbox;
import com.example.bestpractices.repository.user.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class AbstractTest {

    protected final ObjectMapper objectMapper = new ObjectMapper();

    protected final User expectedUser = UserBuilder.buildUser();
    protected final UserRequest expectedUserRequest = UserBuilder.buildUserRequest();
    protected final UserResponse expectedUserResponse = UserBuilder.buildUserResponse();
    protected final UserOutbox expectedUserOutbox = UserBuilder.buildUserOutbox();

    protected final LoginRequest expectedLoginRequest = LoginBuilder.buildLoginRequest();
    protected final LoginResponse expectedLoginResponse = LoginBuilder.buildLoginResponse();

    protected final String expectedUserId = UUID.randomUUID().toString();
    protected final String expectedEmail = "email@gmail.com";

    protected final String expectedSecret = "eyJhbGciOiJIUzUxMiJ9eyJzdWIiOiJyb290MiIsImlhdCI6MTYyMzA5ODkzNiwiZXhwIjoxNjIzMTE2OTM2fQUlFvXP9d3KD5UvSxaswq1Km52UCTbBML2BPx6D0qgO43aaUGPKakBFZkYNxHSM1OU8uO7W9Ry07EiWyd4E1g";
    protected final Integer expectedTokenLength = 195;

}
