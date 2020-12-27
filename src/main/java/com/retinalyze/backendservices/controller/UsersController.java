package com.retinalyze.backendservices.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retinalyze.backendservices.model.User;
import com.retinalyze.backendservices.model.UsersFound;

@RestController
@RequestMapping("/users")
public class UsersController {

    @RequestMapping("/find/{term}")
    private ResponseEntity<UsersFound> findUsers(@PathVariable("term") String term) {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "mom@retinalyze.com"));
        users.add(new User(2, "pmattioli@polyglot-outsourcing.com"));
        return ResponseEntity.ok(new UsersFound(users));
    }



}
