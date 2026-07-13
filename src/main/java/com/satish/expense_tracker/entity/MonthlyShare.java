package com.satish.expense_tracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "monthly_share")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonthlyShare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Integer billingMonth;

    @Column(nullable = false)
    private Integer billingYear;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monthlyShare;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal paidAmount;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal dueAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShareStatus status;

    @Column(nullable = false)
    private LocalDate createdDate;

}