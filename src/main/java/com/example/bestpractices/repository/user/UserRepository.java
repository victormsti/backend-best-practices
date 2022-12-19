package com.example.bestpractices.repository.user;

import com.example.bestpractices.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail (String email);
}
