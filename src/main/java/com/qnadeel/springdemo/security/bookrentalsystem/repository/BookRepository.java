package com.qnadeel.springdemo.security.bookrentalsystem.repository;

import com.qnadeel.springdemo.security.bookrentalsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
