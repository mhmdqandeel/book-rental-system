package com.qnadeel.springdemo.security.bookrentalsystem.controller;

import com.qnadeel.springdemo.security.bookrentalsystem.dto.response.UserResponse;
import com.qnadeel.springdemo.security.bookrentalsystem.mapper.UserMapper;
import com.qnadeel.springdemo.security.bookrentalsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long userId) {
        return ResponseEntity
                .ok(userMapper
                        .usertoUserResponse(userService
                                .getUserById(userId)));
    }

    @GetMapping("")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity
                .ok(userMapper
                        .userListToUserResponseList(
                                userService.getAllUser()
                        ));
    }

}
