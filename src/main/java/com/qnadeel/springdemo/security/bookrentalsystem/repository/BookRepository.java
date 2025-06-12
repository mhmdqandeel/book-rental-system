package com.qnadeel.springdemo.security.bookrentalsystem.repository;

import com.qnadeel.springdemo.security.bookrentalsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> getBooksByBookGenre(String genre);

    List<Book> getBooksByBookAuthor(String authorName);

    Book findByBookName(String bookName);
}
