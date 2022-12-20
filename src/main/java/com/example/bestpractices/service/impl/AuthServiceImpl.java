package com.example.bestpractices.service.impl;

import com.example.bestpractices.configuration.exception.UnauthorizedException;
import com.example.bestpractices.configuration.security.JWTUtil;
import com.example.bestpractices.dto.request.LoginRequest;
import com.example.bestpractices.dto.response.LoginResponse;
import com.example.bestpractices.model.user.User;
import com.example.bestpractices.repository.user.UserRepository;
import com.example.bestpractices.service.contract.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Value("${jwt.jwt_secret}")
    private String secret;

    @Value("${jwt.subject}")
    private String subject;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final Long TIME_TO_LIVE =  3600000L;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        if (userOptional.isEmpty()){
            throw new UnauthorizedException();
        }

        User foundUser = userOptional.get();

        if(!passwordEncoder.matches(request.getPassword(), foundUser.getPassword())) {
            throw new UnauthorizedException();
        }

        String token = JWTUtil.generateToken(subject, foundUser.getEmail(), TIME_TO_LIVE, secret);

        return LoginResponse.builder().token(token).build();
    }
}
