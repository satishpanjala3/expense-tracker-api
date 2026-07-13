package com.satish.expense_tracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "billing_settings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillingSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false, unique = true)
    private Room room;

    @Column(nullable = false)
    private Integer billingStartDay;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monthlyShareAmount;

    @Column(nullable = false)
    private LocalDate updatedDate;

}