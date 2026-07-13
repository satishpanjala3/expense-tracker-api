package com.satish.expense_tracker.service;

import com.satish.expense_tracker.dto.request.UpdateMonthlyShareRequest;
import com.satish.expense_tracker.dto.response.BillingDateResponse;
import com.satish.expense_tracker.dto.response.MonthlyShareAmountResponse;
import com.satish.expense_tracker.dto.response.MonthlyShareResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

public interface BillingSettingService {

    /**
     * Billing Start Day
     */
    BillingDateResponse getBillingDate();

    void createMonthlyShareForUser(Long userId);

    BillingDateResponse updateBillingStartDay(
            Integer billingStartDay
    );

    /**
     * Monthly Share Amount
     */
    MonthlyShareAmountResponse getMonthlyShareAmount();

    MonthlyShareAmountResponse updateMonthlyShareAmount(
            BigDecimal monthlyShareAmount
    );

    /**
     * Monthly Collection
     */
    List<MonthlyShareResponse> getMonthlyShareCollection();

    /**
     * Receive Payment
     */
    void updateMonthlyShare(
            Long id,
            UpdateMonthlyShareRequest request
    );

}