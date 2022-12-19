package com.example.bestpractices.mapper.contract.user;

import com.example.bestpractices.dto.request.UpdateUserRequest;
import com.example.bestpractices.dto.request.UserRequest;
import com.example.bestpractices.dto.response.user.UserResponse;
import com.example.bestpractices.model.user.User;

import java.util.List;

public interface UserMapper {

    UserResponse toResponse (User user);

    List<UserResponse> toResponse (List<User> users);

    User fromRequest (UserRequest user);

    User fromUpdateRequest (User user, UpdateUserRequest request);
}
