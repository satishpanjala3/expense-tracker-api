package com.satish.expense_tracker.controller;

import com.satish.expense_tracker.dto.request.BillingDateRequest;
import com.satish.expense_tracker.dto.request.MonthlyShareRequest;
import com.satish.expense_tracker.dto.request.UpdateMonthlyShareRequest;
import com.satish.expense_tracker.dto.response.BillingDateResponse;
import com.satish.expense_tracker.dto.response.MonthlyShareAmountResponse;
import com.satish.expense_tracker.dto.response.MonthlyShareResponse;
import com.satish.expense_tracker.service.BillingSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://expense-tracker-gamma-rouge.vercel.app")
public class BillingSettingController {

    private final BillingSettingService billingSettingService;

    /**
     * Get Billing Start Day
     */
    @GetMapping("/billing-date")
    public ResponseEntity<BillingDateResponse> getBillingDate() {

        return ResponseEntity.ok(
                billingSettingService.getBillingDate()
        );

    }

    /**
     * Update Billing Start Day
     */
    @PutMapping("/billing-date")
    public ResponseEntity<BillingDateResponse> updateBillingDate(
            @RequestBody BillingDateRequest request
    ) {

        return ResponseEntity.ok(
                billingSettingService.updateBillingStartDay(
                        request.getBillingStartDay()
                )
        );

    }

    /**
     * Get Monthly Share Amount
     */
    @GetMapping("/monthly-share-amount")
    public ResponseEntity<MonthlyShareAmountResponse> getMonthlyShareAmount() {

        return ResponseEntity.ok(
                billingSettingService.getMonthlyShareAmount()
        );

    }

    /**
     * Update Monthly Share Amount
     */
    @PutMapping("/monthly-share-amount")
    public ResponseEntity<MonthlyShareAmountResponse> updateMonthlyShareAmount(
            @RequestBody MonthlyShareRequest request
    ) {

        return ResponseEntity.ok(
                billingSettingService.updateMonthlyShareAmount(
                        request.getMonthlyShareAmount()
                )
        );

    }

    /**
     * Monthly Share Collection
     */
    @GetMapping("/monthly-share")
    public ResponseEntity<List<MonthlyShareResponse>> getMonthlyShares() {

        return ResponseEntity.ok(
                billingSettingService.getMonthlyShareCollection()
        );

    }

    /**
     * Receive Monthly Share Payment
     */
    @PutMapping("/monthly-share/{id}")
    public ResponseEntity<String> updateMonthlyShare(

            @PathVariable Long id,

            @RequestBody UpdateMonthlyShareRequest request

    ) {

        billingSettingService.updateMonthlyShare(id, request);

        return ResponseEntity.ok(
                "Monthly share updated successfully."
        );

    }

}
