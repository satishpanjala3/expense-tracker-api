package com.satish.expense_tracker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonthlyShareResponse {

    private Long id;

    private Long userId;

    private String memberName;

    private BigDecimal monthlyShare;

    private BigDecimal paidAmount;

    private BigDecimal dueAmount;

    private String status;

}