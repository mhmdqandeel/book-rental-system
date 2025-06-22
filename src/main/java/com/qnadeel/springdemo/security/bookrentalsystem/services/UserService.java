package com.qnadeel.springdemo.security.bookrentalsystem.services;

import com.qnadeel.springdemo.security.bookrentalsystem.dtos.UserLogin;
import com.qnadeel.springdemo.security.bookrentalsystem.dtos.request.CreateAccountRequest;
import com.qnadeel.springdemo.security.bookrentalsystem.exceptions.ResourcesNotFoundException;
import com.qnadeel.springdemo.security.bookrentalsystem.entities.Rental;
import com.qnadeel.springdemo.security.bookrentalsystem.entities.User;
import com.qnadeel.springdemo.security.bookrentalsystem.repositories.UserRepository;
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

    private final JwtService jwtService;

    public void createAccount(CreateAccountRequest createAccountRequest) {

        if (userRepository.existsByUserNameOrUserEmail(createAccountRequest.getUserName(),
                createAccountRequest.getUserEmail()))
        {
            throw new ResourcesNotFoundException("Username or Email already exists");
        }

        User user = User.builder()
                .userEmail(createAccountRequest.getUserEmail())
                .userName(createAccountRequest.getUserName())
                .userPassword(passwordEncoder.encode(createAccountRequest.getUserPassword()))
                .build();

        userRepository.save(user);

    }

    public String login(UserLogin userLogin) {

        User user = getUserByUsername(userLogin.getUserName());

        if (!(passwordEncoder.matches(userLogin.getUserPassword(), user.getUserPassword()))){
            throw new BadCredentialsException("Invalid password");
        }
        return jwtService.generateToken(user.getUserEmail(), user.getUserName());
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
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResourcesNotFoundException("There are no users");
        }
        return users;
    }

    public User updateUser(Long userId, CreateAccountRequest createAccountRequest) {

        User user = getUserById(userId);

        user.setUserEmail(createAccountRequest.getUserEmail());
        user.setUserName(createAccountRequest.getUserName());
        user.setUserPassword(passwordEncoder.encode(createAccountRequest.getUserPassword()));

        return userRepository.save(user);
    }

    public List<Rental> getRentals(Long userId) {

        User user = getUserById(userId);

        return user.getRentals();
    }

    public Optional<User> findUserByEmail(String username) {
        return userRepository.findByUserEmail(username);
    }
}
