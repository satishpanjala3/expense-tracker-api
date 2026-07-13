package com.satish.expense_tracker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponse {

    private String roomName;

    private Long totalMembers;

    private Long activeMembers;

    private Long pendingRequests;

    private Double totalExpenses;

    private Double currentMonthExpenses;

}