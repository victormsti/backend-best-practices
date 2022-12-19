package com.example.bestpractices.repository;

import com.example.bestpractices.base.AbstractTest;
import com.example.bestpractices.model.user.User;
import com.example.bestpractices.repository.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UserRepositoryIT extends AbstractTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenSavingUserInDBThenShouldRetrieveItSuccessfully(){
        entityManager.persist(expectedUser);
        entityManager.flush();

        Optional<User> userOptional = userRepository.findByEmail(expectedUser.getEmail());

        assertTrue(userOptional.isPresent());
    }
}
