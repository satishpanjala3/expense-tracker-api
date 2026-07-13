package com.satish.expense_tracker.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileResponse {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private String role;

    private String status;

    private String roomName;

}