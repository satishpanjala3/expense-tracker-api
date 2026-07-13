package com.satish.expense_tracker.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyShareAmountResponse {

    private BigDecimal monthlyShareAmount;

}
