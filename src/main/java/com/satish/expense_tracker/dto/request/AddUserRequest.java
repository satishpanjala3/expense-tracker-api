package com.satish.expense_tracker.dto.request;

import lombok.Data;

@Data
public class AddUserRequest {

    private String name;

    private String email;

    private String phone;

    private String password;

    private String role;

}