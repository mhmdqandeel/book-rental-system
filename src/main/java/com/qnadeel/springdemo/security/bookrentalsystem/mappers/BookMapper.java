package com.qnadeel.springdemo.security.bookrentalsystem.mappers;

import com.qnadeel.springdemo.security.bookrentalsystem.dtos.BookDto;
import com.qnadeel.springdemo.security.bookrentalsystem.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Builder
@Data
public class BookMapper {

    public BookDto bookToBookDto(Book book) {

        return BookDto.builder()
                .bookName(book.getBookName())
                .bookAuthor(book.getBookAuthor())
                .bookGenre(book.getBookGenre())
                .bookDescription(book.getBookDescription())
                .bookPrice(book.getBookPrice())
                .bookStock(book.getBookStock())
                .build();
    }

    public List<BookDto> listOfBooksToListOfBookDto(List<Book> books) {
        return books.stream()
                .map(this::bookToBookDto)
                .toList();
    }
}
