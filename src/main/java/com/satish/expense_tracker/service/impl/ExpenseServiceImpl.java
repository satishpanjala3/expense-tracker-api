package com.satish.expense_tracker.service.impl;

import com.satish.expense_tracker.dto.request.ExpenseItemRequest;
import com.satish.expense_tracker.dto.request.ExpenseRequest;
import com.satish.expense_tracker.dto.response.ExpenseItemResponse;
import com.satish.expense_tracker.dto.response.ExpenseResponse;
import com.satish.expense_tracker.entity.*;
import com.satish.expense_tracker.repository.ExpenseRepository;
import com.satish.expense_tracker.repository.UserRepository;
import com.satish.expense_tracker.service.ExpenseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    private final UserRepository userRepository;

    @Override
    public ExpenseResponse addExpense(ExpenseRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Expense expense = Expense.builder()
                .user(user)
                .category(request.getCategory())
                .amount(request.getAmount())
                .description(request.getDescription())
                .expenseDate(LocalDate.now())
                .status(ExpenseStatus.PENDING)
                .build();

        List<ExpenseItem> items = request.getItems()
                .stream()
                .map(itemRequest -> {

                    ExpenseItem item = new ExpenseItem();

                    item.setItemName(itemRequest.getItemName());
                    item.setQuantity(itemRequest.getQuantity());
                    item.setPrice(itemRequest.getPrice());
                    item.setExpense(expense);

                    return item;

                }).toList();

        expense.setItems(items);

        Expense savedExpense = expenseRepository.save(expense);

        return mapToResponse(savedExpense);
    }

    @Override
    public List<ExpenseResponse> getAllExpenses() {

        return expenseRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();

    }

    @Override
    public ExpenseResponse getExpenseById(Long id) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        return mapToResponse(expense);

    }

    @Override
    public ExpenseResponse updateExpense(Long id,
                                         ExpenseRequest request) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        expense.setCategory(request.getCategory());
        expense.setAmount(request.getAmount());
        expense.setDescription(request.getDescription());
        expense.setStatus(expense.getStatus());

        expense.getItems().clear();

        List<ExpenseItem> items = request.getItems()
                .stream()
                .map(itemRequest -> {

                    ExpenseItem item = new ExpenseItem();

                    item.setItemName(itemRequest.getItemName());
                    item.setQuantity(itemRequest.getQuantity());
                    item.setPrice(itemRequest.getPrice());
                    item.setExpense(expense);

                    return item;

                }).toList();

        expense.getItems().addAll(items);

        Expense updated = expenseRepository.save(expense);

        return mapToResponse(updated);

    }

    @Override
    public void deleteExpense(Long id) {

        expenseRepository.deleteById(id);

    }
    @Override
    public List<ExpenseResponse> getPendingExpenses() {

        return expenseRepository
                .findByStatus(ExpenseStatus.PENDING)
                .stream()
                .map(this::mapToResponse)
                .toList();

    }
    @Override
    public ExpenseResponse approveExpense(Long id) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found."));

        expense.setStatus(ExpenseStatus.APPROVED);

        expenseRepository.save(expense);

        return mapToResponse(expense);

    }
    @Override
    public ExpenseResponse rejectExpense(Long id) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found."));

        expense.setStatus(ExpenseStatus.REJECTED);

        expenseRepository.save(expense);

        return mapToResponse(expense);

    }

    private ExpenseResponse mapToResponse(Expense expense) {

        return ExpenseResponse.builder()
                .id(expense.getId())
                .userId(expense.getUser().getId())
                .userName(expense.getUser().getName())
                .category(expense.getCategory())
                .amount(expense.getAmount())
                .description(expense.getDescription())
                .expenseDate(expense.getExpenseDate())
                .status(expense.getStatus().name())
                .items(expense.getItems()
                        .stream()
                        .map(item -> ExpenseItemResponse.builder()
                                        .itemName(item.getItemName())
                                        .quantity(item.getQuantity())
                                        .price(item.getPrice())
                                        .build()).toList()).build();


    }

}