package com.qnadeel.springdemo.security.bookrentalsystem.controller;

import com.qnadeel.springdemo.security.bookrentalsystem.dto.BookDto;
import com.qnadeel.springdemo.security.bookrentalsystem.mapper.BookMapper;
import com.qnadeel.springdemo.security.bookrentalsystem.mapper.UserMapper;
import com.qnadeel.springdemo.security.bookrentalsystem.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    private final BookMapper bookMapper;

    @Operation(
            summary = "Add a new book",
            description = "Creates a new book entry in the system with the given book details."
    )
    @PostMapping("/")
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
        return ResponseEntity
                .ok(bookMapper
                        .bookToBookDto(bookService
                                .addBook(bookDto)));
    }

    @Operation(
            summary = "Update book details",
            description = "Updates an existing book's information by its ID with the provided details."
    )
    @PutMapping("/{bookId}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long bookId, @RequestBody BookDto bookDto) {
        return ResponseEntity
                .ok(bookMapper
                        .bookToBookDto(bookService
                                .updateBook(bookId ,bookDto)));
    }

    @Operation(
            summary = "Get all books",
            description = "Retrieves a list of all books available in the system."
    )
    @GetMapping("/")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity
                .ok(bookMapper
                        .listOfBooksToListOfBookDto(bookService
                                .getAllBooks()));
    }

    @Operation(
            summary = "Get a book by ID",
            description = "Retrieves a single book's details using its unique ID."
    )
    @GetMapping("/{bookId}")
    public ResponseEntity<BookDto> getBook(@PathVariable Long bookId) {
        return ResponseEntity
                .ok(bookMapper
                        .bookToBookDto(bookService
                                .getBookById(bookId)));
    }

    @Operation(
            summary = "Delete a book",
            description = "Deletes a book from the system using its unique ID."
    )
    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable Long bookId) {
        bookService.deleteBookById(bookId);
        return ResponseEntity.ok("");
    }

    @GetMapping("book-by-genre")
    public ResponseEntity<List<BookDto>> getBooksByGenre(@RequestBody String genre) {
        return ResponseEntity
                .ok(bookMapper
                        .listOfBooksToListOfBookDto(bookService
                                .getBooksByGenre(genre)));
    }

    @GetMapping("book-by-author")
    public ResponseEntity<List<BookDto>> getBooksByAuthor(@RequestBody String authorName) {
        return ResponseEntity
                .ok(bookMapper
                        .listOfBooksToListOfBookDto(bookService
                                .getBooksByAuthor(authorName)));
    }

    @GetMapping("book-by-name")
    public ResponseEntity<BookDto> getBookByName(@RequestBody String bookName) {
        return ResponseEntity
                .ok(bookMapper
                        .bookToBookDto(bookService
                                .getBookByName(bookName)));
    }
}