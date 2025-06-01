package com.qnadeel.springdemo.security.bookrentalsystem.service;

import com.qnadeel.springdemo.security.bookrentalsystem.dto.UserLogin;
import com.qnadeel.springdemo.security.bookrentalsystem.dto.request.CreateAccountRequest;
import com.qnadeel.springdemo.security.bookrentalsystem.exeption.ResourcesNotFoundException;
import com.qnadeel.springdemo.security.bookrentalsystem.model.User;
import com.qnadeel.springdemo.security.bookrentalsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void createAccount(CreateAccountRequest createAccountRequest) {

        if (userRepository.existsByUserNameAndUserEmail(createAccountRequest.getUserName(),
                createAccountRequest.getUserEmail()))
        {
            throw new ResourcesNotFoundException("Username or Email already exists");
        }

        User user = new User();

        user.setUserEmail(createAccountRequest.getUserEmail());
        user.setUserName(createAccountRequest.getUserName());
        user.setUserPassword(passwordEncoder.encode(createAccountRequest.getUserPassword()));

        userRepository.save(user);
    }

    public void login(UserLogin userLogin) {

        User user = getUserByUsername(userLogin.getUserName());

        if (!(passwordEncoder.matches(userLogin.getPassword(), user.getUserPassword()))){
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    public User getUserByUsername (String username) {
        return findUserByUsername(username)
                .orElseThrow(() -> new ResourcesNotFoundException("User not found"));
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourcesNotFoundException("User not found"));
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
