package com.satish.expense_tracker.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "expense_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    private Integer quantity;

    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expense_id")
    private Expense expense;

}