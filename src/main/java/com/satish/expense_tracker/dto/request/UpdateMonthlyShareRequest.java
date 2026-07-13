package com.satish.expense_tracker.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateMonthlyShareRequest {

    private BigDecimal receivedAmount;

}