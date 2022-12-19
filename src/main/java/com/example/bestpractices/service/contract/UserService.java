package com.example.bestpractices.service.contract;

import com.example.bestpractices.dto.request.UpdateUserRequest;
import com.example.bestpractices.dto.request.UserRequest;
import com.example.bestpractices.dto.response.ResponseMessage;
import com.example.bestpractices.dto.response.user.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest request);

    UserResponse getUserById(String userId);

    List<UserResponse> getAllUsers();

    ResponseMessage deleteUserById(String userId);

    UserResponse updateUserById(String userId, UpdateUserRequest request);
}
