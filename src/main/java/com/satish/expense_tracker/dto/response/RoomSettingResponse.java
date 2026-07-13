package com.satish.expense_tracker.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomSettingResponse {

    private Integer billingStartDay;

    private BigDecimal monthlyShareAmount;

}