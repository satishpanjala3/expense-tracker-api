package com.satish.expense_tracker.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ExpenseRequest {

    private String category;

    private Double amount;

    private String description;

    private Long userId;

    private List<ExpenseItemRequest> items;

}