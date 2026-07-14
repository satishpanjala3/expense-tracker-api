package com.satish.expense_tracker.controller;

import com.satish.expense_tracker.dto.request.ExpenseRequest;
import com.satish.expense_tracker.dto.response.ExpenseResponse;
import com.satish.expense_tracker.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://expense-tracker-gamma-rouge.vercel.app")
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ExpenseResponse> addExpense(
            @RequestBody ExpenseRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(expenseService.addExpense(request));

    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> getAllExpenses() {

        return ResponseEntity.ok(
                expenseService.getAllExpenses());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponse> getExpenseById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                expenseService.getExpenseById(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponse> updateExpense(
            @PathVariable Long id,
            @RequestBody ExpenseRequest request) {

        return ResponseEntity.ok(
                expenseService.updateExpense(id, request));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(
            @PathVariable Long id) {

        expenseService.deleteExpense(id);

        return ResponseEntity.ok(
                "Expense deleted successfully.");

    }

    @GetMapping("/pending")
    public ResponseEntity<List<ExpenseResponse>> getPendingExpenses() {

        return ResponseEntity.ok(
                expenseService.getPendingExpenses());

    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<ExpenseResponse> approveExpense(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                expenseService.approveExpense(id));

    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<ExpenseResponse> rejectExpense(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                expenseService.rejectExpense(id));

    }

}
