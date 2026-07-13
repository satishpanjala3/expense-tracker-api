package com.satish.expense_tracker.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class ExpenseResponse {

    private Long id;

    private Long userId;

    private String userName;

    private String category;

    private Double amount;

    private String description;

    private LocalDate expenseDate;

    private String status;

    private List<ExpenseItemResponse> items;

}