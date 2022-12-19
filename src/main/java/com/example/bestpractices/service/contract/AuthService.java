package com.example.bestpractices.service.contract;

import com.example.bestpractices.dto.request.LoginRequest;
import com.example.bestpractices.dto.response.LoginResponse;
import org.springframework.stereotype.Service;

public interface AuthService {

    LoginResponse login(LoginRequest request);
}
