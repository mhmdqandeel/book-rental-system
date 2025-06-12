package com.qnadeel.springdemo.security.bookrentalsystem.repository;

import com.qnadeel.springdemo.security.bookrentalsystem.model.Rental;
import com.qnadeel.springdemo.security.bookrentalsystem.model.RentalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findRentalsByRentalStatus(RentalStatus rentalStatus);
}
