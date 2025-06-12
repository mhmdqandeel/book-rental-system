package com.qnadeel.springdemo.security.bookrentalsystem.controller;

import com.qnadeel.springdemo.security.bookrentalsystem.dto.MyUserDetails;
import com.qnadeel.springdemo.security.bookrentalsystem.dto.request.CreateAccountRequest;
import com.qnadeel.springdemo.security.bookrentalsystem.dto.response.UserResponse;
import com.qnadeel.springdemo.security.bookrentalsystem.mapper.UserMapper;
import com.qnadeel.springdemo.security.bookrentalsystem.model.Rental;
import com.qnadeel.springdemo.security.bookrentalsystem.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(
            summary = "Get user by ID",
            description = "Fetches the details of a specific user using their unique ID."
    )
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long userId) {
        return ResponseEntity
                .ok(userMapper
                        .usertoUserResponse(userService
                                .getUserById(userId)));
    }

    @Operation(
            summary = "Get all users",
            description = "Returns a list of all registered users in the system."
    )
    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity
                .ok(userMapper
                        .userListToUserResponseList(
                                userService.getAllUser()
                        ));
    }

    @Operation(
            summary = "Update user account",
            description = "Updates the user account information based on the given user ID and new data."
    )
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody CreateAccountRequest createAccountRequest,
                                                   @PathVariable Long userId) {
        return ResponseEntity
                .ok(userMapper
                        .usertoUserResponse((userService
                                .updateUser(userId, createAccountRequest))));
    }

    @Operation(
            summary = "Get current authenticated user",
            description = "Returns the profile of the currently authenticated user."
    )
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(@AuthenticationPrincipal MyUserDetails userDetails) {

        Long userId = userDetails.getId();

        return ResponseEntity
                .ok(userMapper
                        .usertoUserResponse(userService
                                .getUserById(userId)));
    }

    @Operation(
            summary = "Get user rentals",
            description = "Fetches all rentals associated with a specific user by their ID."
    )
    @GetMapping("/{userId}/rentals")
    public ResponseEntity<List<Rental>> getRentals(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getRentals(userId));
    }
}