package com.qnadeel.springdemo.security.bookrentalsystem.exeptions;

public class ResourcesNotFoundException extends RuntimeException {
    public ResourcesNotFoundException(String message) {
        super(message);
    }
}
