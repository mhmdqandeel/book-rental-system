package com.qnadeel.springdemo.security.bookrentalsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "RENTAL")
public class Rental{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private Long rentalId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "rental_books",
            joinColumns = @JoinColumn(name = "rental_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books = new ArrayList<>();

    @Column(name = "rent_date")
    private LocalDate rentDate;

    @Column(name = "rent_return_date")
    private LocalDate returnDate;

    @Column(name = "rent_actual_return_date")
    private LocalDate actualReturnDate;

    @Column(name = "rental_status")
    private RentalStatus rentalStatus;
}
