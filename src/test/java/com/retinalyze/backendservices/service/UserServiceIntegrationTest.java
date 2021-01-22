package com.retinalyze.backendservices.service;

import com.retinalyze.backendservices.model.db.Role;
import com.retinalyze.backendservices.model.db.User;
import com.retinalyze.backendservices.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = UserService.class)
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        User alex = new User(null, "alex", "pass", false, new Role(null, Role.USER_ADMIN));

        Mockito.when(userRepository.findByUsernameContaining(alex.getUsername()))
                .thenReturn(Collections.singletonList(alex));
    }

    @Test
    public void whenValidName_thenUserShouldBeFound() {
        String name = "alex";
        List<User> found = userService.findUsers(name);

        assertThat(found.size()).isEqualTo(1);
        assertThat(found.get(0).getUsername()).isEqualTo("alex");
    }

}
