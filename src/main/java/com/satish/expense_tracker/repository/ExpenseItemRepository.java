package com.satish.expense_tracker.repository;

import com.satish.expense_tracker.entity.ExpenseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpenseItemRepository extends JpaRepository<ExpenseItem, Long> {
    @Query("""
            SELECT DISTINCT ei.itemName FROM ExpenseItem ei ORDER BY ei.itemName
            """)
    List<String> findDistinctItemNames();
}