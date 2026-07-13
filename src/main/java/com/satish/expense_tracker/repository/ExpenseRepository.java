package com.satish.expense_tracker.repository;

import com.satish.expense_tracker.entity.Expense;
import com.satish.expense_tracker.entity.ExpenseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByStatus(ExpenseStatus status);

}