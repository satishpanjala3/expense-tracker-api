package com.satish.expense_tracker.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomSettingRequest {

    private Integer billingStartDay;

    private BigDecimal monthlyShareAmount;

}