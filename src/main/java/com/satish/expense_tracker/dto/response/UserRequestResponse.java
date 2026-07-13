package com.satish.expense_tracker.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestResponse {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private String role;

    private String status;

    private String requestDate;

}