package com.qnadeel.springdemo.security.bookrentalsystem.dtos.response;

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
