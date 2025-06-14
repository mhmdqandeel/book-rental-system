package com.qnadeel.springdemo.security.bookrentalsystem.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RentalRequest {

    private Long bookId;

    private String bookName;
}
