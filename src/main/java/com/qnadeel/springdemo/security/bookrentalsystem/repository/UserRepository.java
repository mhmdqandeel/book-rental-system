package com.qnadeel.springdemo.security.bookrentalsystem.repository;

import com.qnadeel.springdemo.security.bookrentalsystem.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String username);

    boolean existsByUserNameAndUserEmail(String userName, String userEmail);

    Optional<User> findByUserEmail(String username);

    boolean existsByUserNameOrUserEmail(@NotBlank(message = "Username is required") @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters") String userName, @NotBlank(message = "Email is required") @Email(message = "Email should be valid") String userEmail);
}