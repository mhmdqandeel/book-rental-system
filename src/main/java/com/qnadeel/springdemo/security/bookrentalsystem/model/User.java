package com.qnadeel.springdemo.security.bookrentalsystem.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "USER")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String userName;

    private String userPassword;

    private String userEmail;

    private String userRole;

    @OneToMany(mappedBy = "user")
    private List<Rental> rentals;

    private boolean isActive = true;
}