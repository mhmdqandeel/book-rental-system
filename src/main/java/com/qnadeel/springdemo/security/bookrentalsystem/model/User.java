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

//    @PrePersist
//    void userRole(){
//        if (userRole == null){
//            userRole = "ROLE_USER";
//        }
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_role")
    @Builder.Default
    private String userRole = "ROLE_USER";

    @OneToMany(mappedBy = "user")
    private List<Rental> rentals;

    @Builder.Default
    private boolean isActive = true;
}