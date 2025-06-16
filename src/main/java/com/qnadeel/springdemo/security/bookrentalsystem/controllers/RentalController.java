package com.qnadeel.springdemo.security.bookrentalsystem.controllers;

import com.qnadeel.springdemo.security.bookrentalsystem.dtos.MyUserDetails;
import com.qnadeel.springdemo.security.bookrentalsystem.dtos.request.RentalRequest;
import com.qnadeel.springdemo.security.bookrentalsystem.dtos.response.RentalResponse;
import com.qnadeel.springdemo.security.bookrentalsystem.mappers.RentalMapper;
import com.qnadeel.springdemo.security.bookrentalsystem.services.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rentals")
@AllArgsConstructor
public class RentalController {

    private final RentalService rentalService;
    private final RentalMapper rentalMapper;

    @Operation(
            summary = "Rent a book",
            description = "Allows a user to rent a book by providing the user ID and rental details."
    )
    @PostMapping("/")
    public ResponseEntity<RentalResponse> rental(@AuthenticationPrincipal MyUserDetails userDetails,
                                                 @RequestBody RentalRequest rentalRequest) {

        Long userId = userDetails.getId();

        return ResponseEntity
                .ok(rentalMapper
                        .rentalToRentalResponse(rentalService
                                .rental(userId, rentalRequest)));
    }

    @Operation(
            summary = "Return a rented book",
            description = "Allows the authenticated user to return a previously rented book using the rental ID."
    )
    @PostMapping("/{rentalId}/return")
    public ResponseEntity<String> returnRent(@PathVariable Long rentalId,
                                             @AuthenticationPrincipal UserDetails userDetails) {
        String userName = userDetails.getUsername();
        rentalService.returnRent(rentalId, userName);
        return ResponseEntity.ok("Rent returned");
    }

    @Operation(
            summary = "Get all rentals",
            description = "Retrieves a list of all book rentals in the system."
    )
    @GetMapping("/")
    public ResponseEntity<List<RentalResponse>> getAllRentals() {
        return ResponseEntity
                .ok(rentalMapper
                        .rentalsToRentalResponses(rentalService
                                .getAllRentals()));
    }

    @Operation(
            summary = "Get overdue rentals",
            description = "Retrieves a list of all overdue rentals (books not returned on time)."
    )
    @GetMapping("/overdue")
    public ResponseEntity<List<RentalResponse>> getOverdueRentals() {
        return ResponseEntity
                .ok(rentalMapper
                        .rentalsToRentalResponses(rentalService
                                .getAllOverdueRentals()));
    }

    @Operation(
            summary = "Get currently rented books",
            description = "Retrieves a list of books that are currently rented and not yet returned."
    )
    @GetMapping("/during-rental")
    public ResponseEntity<List<RentalResponse>> getDuringRentals() {
        return ResponseEntity
                .ok(rentalMapper
                        .rentalsToRentalResponses(rentalService
                                .getAllDuringRentals()));
    }
}