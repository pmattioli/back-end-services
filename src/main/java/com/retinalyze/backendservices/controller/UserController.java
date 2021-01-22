package com.retinalyze.backendservices.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.retinalyze.backendservices.model.db.Role;
import com.retinalyze.backendservices.service.UserService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retinalyze.backendservices.model.db.User;
import com.retinalyze.backendservices.model.dto.UsersDTO;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/users")
@RolesAllowed(Role.USER_ADMIN)
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/find/{term}")
    @RolesAllowed(Role.USER_ADMIN)
    private ResponseEntity<UsersDTO> findUsers(@PathVariable("term") String term) {
        log.info("Received findUsers request with term: " + term);
        List<User> users = userService.findUsers(term);
        List<String> usernames = users.stream()
                .map(User::getUsername).collect(Collectors.toList());
        if (usernames.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No users found where the term '" + term + "' is included");
        }
        return ResponseEntity.ok(new UsersDTO(usernames));
    }

}
