package com.qnadeel.springdemo.security.bookrentalsystem.service;

import com.qnadeel.springdemo.security.bookrentalsystem.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
}
