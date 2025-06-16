package com.qnadeel.springdemo.security.bookrentalsystem.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private long userId;

    private String userName;

    private String userEmail;

    private boolean isActive;
}
