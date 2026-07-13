package com.satish.expense_tracker.repository;

import com.satish.expense_tracker.entity.MonthlyShare;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MonthlyShareRepository
        extends JpaRepository<MonthlyShare, Long> {

    List<MonthlyShare> findByBillingMonthAndBillingYear(
            Integer billingMonth,
            Integer billingYear
    );

    Optional<MonthlyShare> findByUserIdAndBillingMonthAndBillingYear(
            Long userId,
            Integer billingMonth,
            Integer billingYear
    );

}