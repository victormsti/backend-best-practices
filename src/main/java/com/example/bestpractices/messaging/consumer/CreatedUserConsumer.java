package com.example.bestpractices.messaging.consumer;

import com.example.bestpractices.dto.response.user.UserOutboxResponse;
import org.springframework.stereotype.Component;

@Component
public class CreatedUserConsumer {

    public void consumeMessage(UserOutboxResponse message) {
        System.out.println("Received <" + message + ">");
    }
}
