package com.example.bestpractices.controller;

import com.example.bestpractices.base.AbstractTest;
import com.example.bestpractices.model.user.User;
import com.example.bestpractices.repository.user.UserRepository;
import com.example.bestpractices.service.contract.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserControllerIT extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenCallingMethodCreateUserThenShouldCreateUserSuccessFullyAndFindByEmail() throws Exception {
        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expectedUserRequest)))
                .andExpect(status().isCreated());

        User foundUser = userRepository.findByEmail(expectedUserRequest.getEmail()).get();

        assertEquals(expectedEmail, foundUser.getEmail());
    }
}
