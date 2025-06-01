package com.qnadeel.springdemo.security.bookrentalsystem.service;

import com.qnadeel.springdemo.security.bookrentalsystem.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
}
