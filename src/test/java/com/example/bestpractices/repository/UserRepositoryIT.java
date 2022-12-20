package com.example.bestpractices.repository;

import com.example.bestpractices.base.AbstractTest;
import com.example.bestpractices.model.user.User;
import com.example.bestpractices.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryIT extends AbstractTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    private final String EMAIL_TO_UPDATE = "updatedemail@gmal.com";

    @Test
    public void whenSavingUserInDBThenShouldRetrieveItSuccessfully(){
        entityManager.persist(expectedUser);
        entityManager.flush();

        Optional<User> userOptional = userRepository.findByEmail(expectedUser.getEmail());

        assertTrue(userOptional.isPresent());
    }

    @Test
    public void whenDeletingUserInDBThenShouldDeleteItSuccessfully(){
        entityManager.persist(expectedUser);
        entityManager.flush();

        Optional<User> userOptional = userRepository.findByEmail(expectedUser.getEmail());
        assertTrue(userOptional.isPresent());

        entityManager.remove(expectedUser);
        entityManager.flush();

        Optional<User> userOptionalAfterDelete = userRepository.findByEmail(expectedUser.getEmail());
        assertFalse(userOptionalAfterDelete.isPresent());
    }

    @Test
    public void whenUpdatingUserInDBThenShouldUpdateItSuccessfully(){
        entityManager.persist(expectedUser);
        entityManager.flush();

        Optional<User> userOptional = userRepository.findByEmail(expectedUser.getEmail());
        assertTrue(userOptional.isPresent());

        User userToUpdate = userOptional.get();
        userToUpdate.setEmail(EMAIL_TO_UPDATE);

        entityManager.persist(userToUpdate);
        entityManager.flush();

        Optional<User> userAfterUpdateOptional = userRepository.findByEmail(expectedUser.getEmail());
        assertTrue(userAfterUpdateOptional.isPresent());
        assertEquals(EMAIL_TO_UPDATE, userAfterUpdateOptional.get().getEmail());
    }

    @Test
    public void whenGettingUsersFromDBThenShouldRetrieveSuccessfully(){
        entityManager.persist(expectedUser);
        entityManager.flush();

        entityManager.persist(expectedSecondUser);
        entityManager.flush();

        List<User> users = userRepository.findAll();
        assertFalse(users.isEmpty());
    }
}
