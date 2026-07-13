package com.satish.expense_tracker.dto.request;

import lombok.Data;

@Data
public class UpdateUserRequest {

    private String name;

    private String phone;

    private String role;

    private String status;

}