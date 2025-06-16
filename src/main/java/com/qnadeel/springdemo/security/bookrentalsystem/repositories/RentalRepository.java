package com.qnadeel.springdemo.security.bookrentalsystem.repositories;

import com.qnadeel.springdemo.security.bookrentalsystem.entities.Rental;
import com.qnadeel.springdemo.security.bookrentalsystem.entities.RentalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findRentalsByRentalStatus(RentalStatus rentalStatus);
}
