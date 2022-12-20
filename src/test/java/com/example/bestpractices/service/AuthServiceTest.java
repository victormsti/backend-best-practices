package com.example.bestpractices.service;

import com.example.bestpractices.base.AbstractTest;
import com.example.bestpractices.dto.response.LoginResponse;
import com.example.bestpractices.repository.user.UserRepository;
import com.example.bestpractices.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

public class AuthServiceTest extends AbstractTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    public void whenCallMethodLoginUserThenShouldLoginSuccessFully(){

        ReflectionTestUtils.setField(authService, "secret", expectedSecret);
        ReflectionTestUtils.setField(authService, "subject", expectedSubject);

        Mockito.when(userRepository.findByEmail(expectedEmail)).thenReturn(Optional.of(expectedUser));
        Mockito.when(passwordEncoder.matches(expectedLoginRequest.getPassword(),expectedUser.getPassword())).thenReturn(Boolean.TRUE);

        LoginResponse actualLoginResponse = authService.login(expectedLoginRequest);

        Assertions.assertFalse(actualLoginResponse.getToken().isEmpty());
    }
}
