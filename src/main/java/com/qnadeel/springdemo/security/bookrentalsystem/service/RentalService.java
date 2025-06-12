package com.qnadeel.springdemo.security.bookrentalsystem.service;

import com.qnadeel.springdemo.security.bookrentalsystem.dto.request.RentalRequest;
import com.qnadeel.springdemo.security.bookrentalsystem.exeption.ResourcesNotFoundException;
import com.qnadeel.springdemo.security.bookrentalsystem.model.Book;
import com.qnadeel.springdemo.security.bookrentalsystem.model.Rental;
import com.qnadeel.springdemo.security.bookrentalsystem.model.RentalStatus;
import com.qnadeel.springdemo.security.bookrentalsystem.model.User;
import com.qnadeel.springdemo.security.bookrentalsystem.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;

    private final BookService bookService;

    private final UserService userService;

    public Rental rental(Long userId, RentalRequest rentalRequest) {

        Book book = bookService.getBookById(rentalRequest.getBookId());

        if (book.getBookStock() == 0){
            throw new ResourcesNotFoundException("Book does not exist");
        }

        book.setBookStock(book.getBookStock() - 1);

        Rental rental = new Rental();

        LocalDate currentDate = LocalDate.now();

        rental.setRentDate(currentDate);
        rental.setReturnDate(currentDate.plusDays(14));
        rental.setBooks(List.of(book));

        User user = userService.getUserById(userId);

        rental.setUser(user);
        user.getRentals().add(rental);

        rental.setRentalStatus(RentalStatus.DURING_RENTAL);

        return rentalRepository.save(rental);
    }



    public Rental getRentalById(Long rentalId) {
        return rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental not found"));
    }

    public void returnRent(Long rentalId, String userName) {

        Rental rental = getRentalById(rentalId);
        User user = userService.getUserByUsername(userName);

        if (!rental.getUser().getUserId().equals(user.getUserId())){
            throw new RuntimeException("User does not belong to this rental");
        }

        if (rental.getActualReturnDate() != null){
            throw new RuntimeException("Rental has already returned");
        }

        rental.setActualReturnDate(LocalDate.now());

        rental.getBooks().forEach(book ->{
                book.setBookStock(book.getBookStock() + 1);
            } );


        if (rental.getReturnDate().isBefore(LocalDate.now())){
            rental.setRentalStatus(RentalStatus.OVERDUE);
        }else {
            rental.setRentalStatus(RentalStatus.RETURNED_ON_TIME);
        }

            rentalRepository.save(rental);
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public List<Rental> getAllOverdueRentals() {

        return rentalRepository
                .findRentalsByRentalStatus(RentalStatus.OVERDUE);
    }

    public List<Rental> getAllDuringRentals() {

        return rentalRepository
                .findRentalsByRentalStatus(RentalStatus.DURING_RENTAL);
    }
}
