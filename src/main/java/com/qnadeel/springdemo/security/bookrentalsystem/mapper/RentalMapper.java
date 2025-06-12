package com.qnadeel.springdemo.security.bookrentalsystem.mapper;

import com.qnadeel.springdemo.security.bookrentalsystem.dto.response.RentalResponse;
import com.qnadeel.springdemo.security.bookrentalsystem.model.Book;
import com.qnadeel.springdemo.security.bookrentalsystem.model.Rental;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Builder
@Data
public class RentalMapper {

    public RentalResponse rentalToRentalResponse(Rental rental) {
        return RentalResponse.builder()
                .books(rental.getBooks().stream().map(Book::getBookName).toList())
                .rentDate(rental.getRentDate())
                .returnDate(rental.getReturnDate())
                .build();
    }

    public List<RentalResponse> rentalsToRentalResponses(List<Rental> rentals) {
        return rentals.stream()
                .map(this::rentalToRentalResponse)
                .toList();
    }
}
