package com.qnadeel.springdemo.security.bookrentalsystem.dto.response;

import com.qnadeel.springdemo.security.bookrentalsystem.model.Book;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class RentalResponse {

    private List<String> books;

    private LocalDate rentDate;

    private LocalDate returnDate;
}
