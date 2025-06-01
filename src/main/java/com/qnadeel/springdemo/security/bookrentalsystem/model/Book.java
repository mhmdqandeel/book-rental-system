package com.qnadeel.springdemo.security.bookrentalsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "BOOK")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int bookId;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "book_author")
    private String bookAuthor;

    @Column(name = "book_genre")
    private String bookGenre;

    @Column(name = "book_description")
    private String bookDescription;

    @Column(name = "book_stock")
    private int bookStock;

    @Column(name = "book_price")
    private int bookPrice;

    @OneToMany(mappedBy = "book")
    private List<Rental> rentals;
}
