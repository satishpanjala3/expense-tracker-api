package com.satish.expense_tracker.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExpenseItemResponse {

    private String itemName;

    private Integer quantity;

    private Double price;

}