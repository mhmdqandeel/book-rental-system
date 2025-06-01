package com.qnadeel.springdemo.security.bookrentalsystem.exeption;

public class ResourcesNotFoundException extends RuntimeException {
    public ResourcesNotFoundException(String message) {
        super(message);
    }
}
