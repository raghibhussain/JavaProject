package com.example.WaterConnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.WaterConnect.model.User;
import com.example.WaterConnect.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repo;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return repo.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User u = repo.findByEmail(user.getEmail());
        if (u != null && u.getPassword().equals(user.getPassword()))
            return "Login Successful";
        return "Invalid Credentials";
    }
}