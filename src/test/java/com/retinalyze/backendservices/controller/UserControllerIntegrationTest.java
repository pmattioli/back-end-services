package com.retinalyze.backendservices.controller;

import com.retinalyze.backendservices.model.db.Role;
import com.retinalyze.backendservices.model.db.User;
import com.retinalyze.backendservices.repository.UserRepository;
import com.retinalyze.backendservices.security.JwtTokenUtil;
import com.retinalyze.backendservices.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService service;

    @MockBean
    private JwtTokenUtil tokenUtil;

    @MockBean
    private UserRepository userRepository;

    @Test
    @WithMockUser("mom@retinalyze.com")
    public void givenUsers_whenFindUsers_thenReturnJsonArrayWithUsernames()
            throws Exception {

        User alex = new User(1L, "alex", "pass", false, new Role(null, Role.USER_ADMIN));

        List<User> allUsers = Collections.singletonList(alex);

        String term = "alex";

        given(service.findUsers(term)).willReturn(Collections.singletonList(alex));

        mvc.perform(get("/users/find/" + term)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usersFound", hasSize(1)))
                .andExpect(jsonPath("$.usersFound[0]", is(alex.getUsername())));
    }
}