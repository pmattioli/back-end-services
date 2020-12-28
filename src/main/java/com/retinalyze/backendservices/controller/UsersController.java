package com.retinalyze.backendservices.controller;

import java.util.ArrayList;
import java.util.List;

import com.retinalyze.backendservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retinalyze.backendservices.model.User;
import com.retinalyze.backendservices.model.UsersFound;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController (UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/find/{term}")
    private ResponseEntity<UsersFound> findUsers(@PathVariable("term") String term) {
        List<User> users = userService.findUsers(term);
        return ResponseEntity.ok(new UsersFound(users));
    }



}
