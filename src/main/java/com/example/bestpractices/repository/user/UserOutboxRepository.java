package com.example.bestpractices.repository.user;

import com.example.bestpractices.model.user.UserOutbox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOutboxRepository extends JpaRepository<UserOutbox, String> {

}
