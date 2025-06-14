package com.qnadeel.springdemo.security.bookrentalsystem.service;

import com.qnadeel.springdemo.security.bookrentalsystem.dto.MyUserDetails;
import com.qnadeel.springdemo.security.bookrentalsystem.exeption.ResourcesNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public MyUserDetailsService(@Lazy UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findUserByUsername(username)
                .map(MyUserDetails::new)
                .orElseThrow(() -> new ResourcesNotFoundException("User " + username + " not found"));
    }
}
