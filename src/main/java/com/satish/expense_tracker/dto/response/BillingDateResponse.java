package com.satish.expense_tracker.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillingDateResponse {

    private Integer billingStartDay;

    private String currentBillingStartDate;

    private String currentBillingEndDate;

}