package com.qnadeel.springdemo.security.bookrentalsystem.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDto {

    @NotBlank(message = "Book name is required")
    @Size(max = 100, message = "Book name must be less than 100 characters")
    private String bookName;

    @NotBlank(message = "Book author is required")
    @Size(max = 100, message = "Book author must be less than 100 characters")
    private String bookAuthor;

    @NotBlank(message = "Book genre is required")
    @Size(max = 50, message = "Book genre must be less than 50 characters")
    private String bookGenre;

    @NotBlank(message = "Book description is required")
    @Size(max = 500, message = "Book description must be less than 500 characters")
    private String bookDescription;

    @NotNull(message = "Book stock is required")
    @Min(value = 0, message = "Book stock cannot be negative")
    private Integer bookStock;

    @NotNull(message = "Book price is required")
    @Min(value = 0, message = "Book price cannot be negative")
    private Integer bookPrice;
}
