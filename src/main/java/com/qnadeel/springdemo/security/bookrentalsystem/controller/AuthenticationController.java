package com.qnadeel.springdemo.security.bookrentalsystem.controller;

import com.qnadeel.springdemo.security.bookrentalsystem.dto.UserLogin;
import com.qnadeel.springdemo.security.bookrentalsystem.dto.request.CreateAccountRequest;
import com.qnadeel.springdemo.security.bookrentalsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLogin userLogin){
        userService.login(userLogin);
        return ResponseEntity.ok("User logged in");
    }

    @PostMapping("/regeneration")
    public ResponseEntity<String> createAccount (@RequestBody CreateAccountRequest createAccountRequest){
        userService.createAccount(createAccountRequest);
        return ResponseEntity.ok("Account created");
    }

}
