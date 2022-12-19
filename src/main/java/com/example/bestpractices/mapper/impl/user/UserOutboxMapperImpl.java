package com.example.bestpractices.mapper.impl.user;

import com.example.bestpractices.dto.response.user.UserOutboxResponse;
import com.example.bestpractices.mapper.contract.user.UserOutboxMapper;
import com.example.bestpractices.model.user.UserOutbox;
import org.springframework.stereotype.Service;

@Service
public class UserOutboxMapperImpl implements UserOutboxMapper {

    @Override
    public UserOutboxResponse toResponse(UserOutbox userOutbox) {
        return UserOutboxResponse.builder()
                .id(userOutbox.getId())
                .email(userOutbox.getEmail())
                .creationDate(userOutbox.getCreationDate())
                .updateDate(userOutbox.getUpdateDate())
                .build();
    }
}
