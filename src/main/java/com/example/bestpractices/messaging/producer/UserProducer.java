package com.example.bestpractices.messaging.producer;

import com.example.bestpractices.mapper.contract.user.UserOutboxMapper;
import com.example.bestpractices.model.user.UserOutbox;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserProducer {

    private final RabbitTemplate rabbitTemplate;
    private final UserOutboxMapper userOutboxMapper;

    @Value("${messaging.created-user.exchange}")
    private String exchange;

    @Value("${messaging.created-user.routing-key}")
    private String routingKey;

    @Autowired
    public UserProducer(RabbitTemplate rabbitTemplate,
                        UserOutboxMapper userOutboxMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.userOutboxMapper = userOutboxMapper;
    }

    public void sendMessage(UserOutbox userOutbox) {
        rabbitTemplate.convertAndSend(exchange, routingKey, userOutboxMapper.toResponse(userOutbox));
    }
}
