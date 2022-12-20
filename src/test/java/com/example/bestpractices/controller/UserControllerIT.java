package com.example.bestpractices.controller;

import com.example.bestpractices.base.AbstractTest;
import com.example.bestpractices.builder.user.UserBuilder;
import com.example.bestpractices.configuration.context.UserContext;
import com.example.bestpractices.model.user.User;
import com.example.bestpractices.repository.user.UserRepository;
import com.example.bestpractices.service.contract.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserControllerIT extends AbstractTest {

    private static final User userFromContext = UserBuilder.buildUser();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    public static void setUp(){
        UserContext.getInstance().setUser(userFromContext);
    }

    @Test
    public void whenCallingUserCRUDOperationsThenShouldExecuteSuccessFully() throws Exception {
        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expectedUserRequest)))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expectedSecondUserRequest)))
                .andExpect(status().isCreated());

        User foundUser = userRepository.findByEmail(expectedUserRequest.getEmail()).get();
        assertEquals(expectedEmail, foundUser.getEmail());

        User foundSecondUser = userRepository.findByEmail(expectedSecondUserRequest.getEmail()).get();
        assertEquals(expectedSecondEmail, foundSecondUser.getEmail());

        UserContext.getInstance().setUser(foundUser);

        mockMvc.perform(delete("/api/v1/users/id/{userId}", foundSecondUser.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Optional<User> userOptional = userRepository.findByEmail(expectedSecondUserRequest.getEmail());
        assertFalse(userOptional.isPresent());

        mockMvc.perform(get("/api/v1/users/id/{userId}", foundUser.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


}
