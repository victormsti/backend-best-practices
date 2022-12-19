package com.example.bestpractices.service.impl;

import com.example.bestpractices.configuration.context.UserContext;
import com.example.bestpractices.configuration.exception.ConflictException;
import com.example.bestpractices.configuration.exception.NotFoundException;
import com.example.bestpractices.dto.request.UpdateUserRequest;
import com.example.bestpractices.dto.request.UserRequest;
import com.example.bestpractices.dto.response.ResponseMessage;
import com.example.bestpractices.dto.response.user.UserResponse;
import com.example.bestpractices.mapper.contract.user.UserMapper;
import com.example.bestpractices.model.user.User;
import com.example.bestpractices.model.user.UserOutbox;
import com.example.bestpractices.repository.user.UserOutboxRepository;
import com.example.bestpractices.repository.user.UserRepository;
import com.example.bestpractices.service.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserOutboxRepository userOutboxRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserOutboxRepository userOutboxRepository,
                           UserMapper userMapper,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userOutboxRepository = userOutboxRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserResponse createUser(UserRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new ConflictException("Email already used");
        }

        User userToSave = userMapper.fromRequest(request);
        passwordEncoder.encode(userToSave.getPassword());

        UserOutbox userOutboxToSave = buildUserOutboxFromUser(userToSave);

        userOutboxRepository.save(userOutboxToSave);

        return userMapper.toResponse(userRepository.save(userToSave));
    }

    private UserOutbox buildUserOutboxFromUser(User user) {
        return UserOutbox.builder()
                .email(user.getEmail())
                .creationDate(user.getCreationDate())
                .updateDate(user.getUpdateDate())
                .build();
    }

    @Override
    public UserResponse getUserById(String userId) {
        return userMapper.toResponse(validateGetUserById(userId));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userMapper.toResponse(userRepository.findAll());
    }

    @Override
    public ResponseMessage deleteUserById(String userId) {
        if(UserContext.getInstance().getUser().getId().equalsIgnoreCase(userId)){
            throw new ConflictException();
        }
        userRepository.delete(validateGetUserById(userId));
        return ResponseMessage.builder().message("User deleted successfully").build();
    }

    @Override
    public UserResponse updateUserById(String userId, UpdateUserRequest request) {
        User foundUser = validateGetUserById(userId);
        foundUser = userMapper.fromUpdateRequest(foundUser, request);

        return userMapper.toResponse(userRepository.save(foundUser));
    }

    private User validateGetUserById(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isEmpty()){
            throw new NotFoundException("User not found");
        }

        return optionalUser.get();
    }

}
