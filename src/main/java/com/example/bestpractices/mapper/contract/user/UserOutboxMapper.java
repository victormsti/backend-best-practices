package com.example.bestpractices.mapper.contract.user;

import com.example.bestpractices.dto.response.user.UserOutboxResponse;
import com.example.bestpractices.model.user.UserOutbox;

public interface UserOutboxMapper {

    UserOutboxResponse toResponse(UserOutbox userOutbox);
}
