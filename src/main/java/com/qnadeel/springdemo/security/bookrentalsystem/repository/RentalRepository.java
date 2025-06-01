package com.qnadeel.springdemo.security.bookrentalsystem.repository;

import com.qnadeel.springdemo.security.bookrentalsystem.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
