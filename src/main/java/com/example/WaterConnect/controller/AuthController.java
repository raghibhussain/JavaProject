package com.example.WaterConnect.controller;

import com.example.WaterConnect.model.*;
import com.example.WaterConnect.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // USER SIGNUP
    @PostMapping("/register/user")
    public User createUser(@RequestBody User user) {
        return authService.registerUser(user);
    }

    // SUPPLIER SIGNUP
    @PostMapping("/register/supplier")
    public Supplier createSupplier(@RequestBody Supplier supplier) {
        return authService.registerSupplier(supplier);
    }

    // CONSUMER SIGNUP
    @PostMapping("/register/consumer")
    public Consumer createConsumer(@RequestBody Consumer consumer) {
        return authService.registerConsumer(consumer);
    }

    // LOGIN
    @PostMapping("/login")
public Object login(@RequestBody User loginRequest) {
    User user = authService.login(loginRequest.getEmail(), loginRequest.getPassword());

    if (user == null) {
        return new ErrorResponse("Invalid email or password");
    }

    return user; // return complete user object
}

class ErrorResponse {
    public String message;
    public ErrorResponse(String msg) { this.message = msg; }
}
}
