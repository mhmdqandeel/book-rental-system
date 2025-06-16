package com.qnadeel.springdemo.security.bookrentalsystem.mappers;

import com.qnadeel.springdemo.security.bookrentalsystem.dtos.response.UserResponse;
import com.qnadeel.springdemo.security.bookrentalsystem.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Builder
@Data
public class UserMapper {

    public UserResponse usertoUserResponse(User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .userEmail(user.getUserEmail())
                .isActive(user.isActive())
                .build();
    }

    public List<UserResponse> userListToUserResponseList(List<User> users) {
        return users.stream()
                .map(this::usertoUserResponse)
                .toList();
    }
}
