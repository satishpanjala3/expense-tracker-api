package com.satish.expense_tracker.dto.request;

import lombok.Data;

@Data
public class ExpenseItemRequest {

    private String itemName;

    private Integer quantity;

    private Double price;

}