package com.satish.expense_tracker.repository;

import com.satish.expense_tracker.entity.ExpenseItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseItemRepository extends JpaRepository<ExpenseItem, Long> {

}