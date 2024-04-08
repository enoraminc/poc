package com.example.demo;

import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {

    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("create")
    public String createUser(@RequestBody User user) throws InterruptedException, ExecutionException {
        return userService.createUser(user);
    }

    @GetMapping("/get")
    public User getUser(@RequestParam String documentId) {
        return userService.getUser(documentId);
    }
}
