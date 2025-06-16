package com.qnadeel.springdemo.security.bookrentalsystem.controllers;

import com.qnadeel.springdemo.security.bookrentalsystem.dtos.UserLogin;
import com.qnadeel.springdemo.security.bookrentalsystem.dtos.request.CreateAccountRequest;
import com.qnadeel.springdemo.security.bookrentalsystem.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @Operation(
            summary = "User Login",
            description = "Authenticates a user using their email and password. Returns a JWT token on successful login."
    )
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLogin userLogin){
        String token = userService.login(userLogin);
        return ResponseEntity.ok(token);
    }

    @Operation(
            summary = "User Registration",
            description = "Registers a new user account with the provided information such as name, email, and password."
    )
    @PostMapping("/registration")
    public ResponseEntity<String> createAccount (@RequestBody CreateAccountRequest createAccountRequest){
        userService.createAccount(createAccountRequest);
        return ResponseEntity.ok("Account created");
    }
}