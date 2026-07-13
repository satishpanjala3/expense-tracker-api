package com.satish.expense_tracker.service;

import com.satish.expense_tracker.dto.request.ExpenseRequest;
import com.satish.expense_tracker.dto.response.ExpenseResponse;

import java.util.List;

public interface ExpenseService {

    ExpenseResponse addExpense(ExpenseRequest request);

    List<ExpenseResponse> getAllExpenses();

    ExpenseResponse getExpenseById(Long id);

    ExpenseResponse updateExpense(Long id, ExpenseRequest request);

    void deleteExpense(Long id);

    List<ExpenseResponse> getPendingExpenses();

    ExpenseResponse approveExpense(Long id);

    ExpenseResponse rejectExpense(Long id);

}