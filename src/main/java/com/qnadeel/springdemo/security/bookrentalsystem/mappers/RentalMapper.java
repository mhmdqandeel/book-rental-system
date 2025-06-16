package com.qnadeel.springdemo.security.bookrentalsystem.mappers;

import com.qnadeel.springdemo.security.bookrentalsystem.dtos.response.RentalResponse;
import com.qnadeel.springdemo.security.bookrentalsystem.entities.Book;
import com.qnadeel.springdemo.security.bookrentalsystem.entities.Rental;
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
