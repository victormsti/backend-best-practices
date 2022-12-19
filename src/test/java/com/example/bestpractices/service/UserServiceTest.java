package com.example.bestpractices.service;

import com.example.bestpractices.base.AbstractTest;
import com.example.bestpractices.dto.response.user.UserResponse;
import com.example.bestpractices.mapper.contract.user.UserMapper;
import com.example.bestpractices.repository.user.UserOutboxRepository;
import com.example.bestpractices.repository.user.UserRepository;
import com.example.bestpractices.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class UserServiceTest extends AbstractTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserOutboxRepository userOutboxRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;


    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void whenCallMethodCreateUserThenShouldCreateUserSuccessFully(){
        Mockito.when(userMapper.fromRequest(expectedUserRequest)).thenReturn(expectedUser);
        Mockito.when(userMapper.toResponse(expectedUser)).thenReturn(expectedUserResponse);
        Mockito.when(userRepository.save(expectedUser)).thenReturn(expectedUser);
        Mockito.when(userOutboxRepository.save(expectedUserOutbox)).thenReturn(expectedUserOutbox);

        UserResponse actualUserResponse = userService.createUser(expectedUserRequest);

        Assertions.assertEquals(expectedUserResponse.getId(), actualUserResponse.getId());
        Assertions.assertEquals(expectedUserResponse.getName(), actualUserResponse.getName());
        Assertions.assertEquals(expectedUserResponse.getEmail(), actualUserResponse.getEmail());
        Assertions.assertEquals(expectedUserResponse.getPassword(), actualUserResponse.getPassword());
        Assertions.assertEquals(expectedUserResponse.getRole(), actualUserResponse.getRole());
        Assertions.assertEquals(expectedUserResponse.getBirthDate(), actualUserResponse.getBirthDate());
        Assertions.assertEquals(expectedUserResponse.getCreationDate(), actualUserResponse.getCreationDate());
        Assertions.assertEquals(expectedUserResponse.getUpdateDate(), actualUserResponse.getUpdateDate());
    }

    @Test
    public void whenCallMethodGetUserByIdThenShouldGetUserSuccessFully(){
        Mockito.when(userMapper.toResponse(expectedUser)).thenReturn(expectedUserResponse);
        Mockito.when(userRepository.findById(expectedUserId)).thenReturn(Optional.of(expectedUser));

        UserResponse actualUserResponse = userService.getUserById(expectedUserId);

        Assertions.assertEquals(expectedUserResponse.getId(), actualUserResponse.getId());
        Assertions.assertEquals(expectedUserResponse.getName(), actualUserResponse.getName());
        Assertions.assertEquals(expectedUserResponse.getEmail(), actualUserResponse.getEmail());
        Assertions.assertEquals(expectedUserResponse.getPassword(), actualUserResponse.getPassword());
        Assertions.assertEquals(expectedUserResponse.getRole(), actualUserResponse.getRole());
        Assertions.assertEquals(expectedUserResponse.getBirthDate(), actualUserResponse.getBirthDate());
        Assertions.assertEquals(expectedUserResponse.getCreationDate(), actualUserResponse.getCreationDate());
        Assertions.assertEquals(expectedUserResponse.getUpdateDate(), actualUserResponse.getUpdateDate());
    }


}
