package com.example.bestpractices.mapper.impl.user;

import com.example.bestpractices.dto.request.UpdateUserRequest;
import com.example.bestpractices.dto.request.UserRequest;
import com.example.bestpractices.dto.response.user.UserResponse;
import com.example.bestpractices.mapper.contract.user.UserMapper;
import com.example.bestpractices.model.user.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .role(user.getRole())
                .birthDate(user.getBirthDate())
                .email(user.getEmail())
                .creationDate(user.getCreationDate())
                .updateDate(user.getUpdateDate())
                .build();
    }

    @Override
    public List<UserResponse> toResponse(List<User> users) {
        List<UserResponse> responseList = new ArrayList<>();
        users.forEach(user -> {
            responseList.add(toResponse(user));
        });

        return responseList;
    }

    @Override
    public User fromRequest(UserRequest user) {
        return User.builder()
                .name(user.getName())
                .role(user.getRole())
                .email(user.getEmail())
                .password(user.getPassword())
                .birthDate(user.getBirthDate())
                .creationDate(LocalDate.now())
                .build();
    }

    @Override
    public User fromUpdateRequest(User user, UpdateUserRequest request) {
        return User.builder()
                .name(request.getName() != null ? request.getName() : user.getName())
                .role(request.getRole() != null ? request.getRole() : user.getRole())
                .email(request.getEmail() != null ? request.getEmail() : user.getEmail())
                .birthDate(request.getBirthDate() != null ? request.getBirthDate() : user.getBirthDate())
                .password(user.getPassword())
                .updateDate(LocalDate.now())
                .creationDate(user.getCreationDate())
                .id(user.getId())
                .build();
    }
}
