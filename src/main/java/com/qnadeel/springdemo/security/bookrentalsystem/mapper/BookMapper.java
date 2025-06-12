package com.qnadeel.springdemo.security.bookrentalsystem.mapper;

import com.qnadeel.springdemo.security.bookrentalsystem.dto.BookDto;
import com.qnadeel.springdemo.security.bookrentalsystem.model.Book;
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
