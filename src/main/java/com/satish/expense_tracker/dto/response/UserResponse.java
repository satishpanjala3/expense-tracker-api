package com.satish.expense_tracker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserResponse {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private String role;

    private String status;

    private String roomName;

    private LocalDate createdDate;

}