package com.example.bestpractices.scheduler;

import com.example.bestpractices.messaging.producer.UserProducer;
import com.example.bestpractices.model.user.UserOutbox;
import com.example.bestpractices.repository.user.UserOutboxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMessageSenderScheduler {

    private final UserOutboxRepository userOutboxRepository;
    private final UserProducer userProducer;

    @Autowired
    public UserMessageSenderScheduler(UserOutboxRepository userOutboxRepository,
                                      UserProducer userProducer) {
        this.userOutboxRepository = userOutboxRepository;
        this.userProducer = userProducer;
    }

    @Scheduled(cron = "${cron.user-message-sender-scheduler}")
    public void sendMessageToCreatedUsers() {
        List<UserOutbox> allMessagesToBeSent = userOutboxRepository.findAll();

        allMessagesToBeSent.forEach(message ->{
            userProducer.sendMessage(message);
            userOutboxRepository.delete(message);
        });
    }
}
