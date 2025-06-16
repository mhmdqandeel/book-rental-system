package com.qnadeel.springdemo.security.bookrentalsystem.services;

import com.qnadeel.springdemo.security.bookrentalsystem.dtos.BookDto;
import com.qnadeel.springdemo.security.bookrentalsystem.exeptions.ResourcesNotFoundException;
import com.qnadeel.springdemo.security.bookrentalsystem.entities.Book;
import com.qnadeel.springdemo.security.bookrentalsystem.repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book addBook(BookDto bookDto) {

        Book book = Book.builder()
                .bookName(bookDto.getBookName())
                .bookAuthor(bookDto.getBookAuthor())
                .bookPrice(bookDto.getBookPrice())
                .bookDescription(bookDto.getBookDescription())
                .bookGenre(bookDto.getBookGenre())
                .bookStock(bookDto.getBookStock())
                .build();

        return bookRepository.save(book);
    }

    public Book updateBook(Long bookId, BookDto bookDto) {

        Book book = getBookById(bookId);

        book.setBookName(bookDto.getBookName());
        book.setBookAuthor(bookDto.getBookAuthor());
        book.setBookPrice(bookDto.getBookPrice());
        book.setBookDescription(bookDto.getBookDescription());
        book.setBookGenre(bookDto.getBookGenre());
        book.setBookStock(bookDto.getBookStock());

        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourcesNotFoundException("Book not found"));
    }

    public void deleteBookById(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public List<Book> getBooksByGenre(String genre) {

        List<Book> books =  bookRepository.getBooksByBookGenre(genre);

        if(books == null || books.isEmpty()) {
            throw new ResourcesNotFoundException("There is no books with genre " + genre);
        }

        return books;
    }

    public List<Book> getBooksByAuthor(String authorName) {
        List<Book> books =  bookRepository.getBooksByBookAuthor(authorName);

        if(books ==null || books.isEmpty()) {
            throw new ResourcesNotFoundException("There is no books with author name " + authorName);
        }
        return books;
    }

    public Book getBookByName(String bookName) {
        return bookRepository.findByBookName(bookName);
    }
}
