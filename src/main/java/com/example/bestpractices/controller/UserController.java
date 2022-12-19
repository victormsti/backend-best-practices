package com.example.bestpractices.controller;

import com.example.bestpractices.dto.request.UpdateUserRequest;
import com.example.bestpractices.dto.request.UserRequest;
import com.example.bestpractices.dto.response.ResponseMessage;
import com.example.bestpractices.dto.response.user.UserResponse;
import com.example.bestpractices.service.contract.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser (@Valid @RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
    }

    @GetMapping("id/{userId}")
    public ResponseEntity<UserResponse> getUserById (@PathVariable String userId) {
        return ResponseEntity.ok().body(userService.getUserById(userId));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers () {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @DeleteMapping("id/{userId}")
    public ResponseEntity<ResponseMessage> deleteUserById (@PathVariable String userId) {
        return ResponseEntity.ok().body(userService.deleteUserById(userId));
    }

    @PutMapping("id/{userId}")
    public ResponseEntity<UserResponse> updateUserById (@PathVariable String userId, @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok().body(userService.updateUserById(userId, request));
    }

}
