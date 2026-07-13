package com.satish.expense_tracker.repository;

import com.satish.expense_tracker.entity.Room;
import com.satish.expense_tracker.entity.BillingSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BillingSettingRepository
        extends JpaRepository<BillingSetting, Long> {
}