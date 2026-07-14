package com.satish.expense_tracker.controller;

import com.satish.expense_tracker.dto.response.DashboardResponse;
import com.satish.expense_tracker.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://expense-tracker-gamma-rouge.vercel.app")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public DashboardResponse getDashboard() {

        return dashboardService.getDashboard();

    }

}
