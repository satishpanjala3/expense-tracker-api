package com.satish.expense_tracker.service.impl;

import com.satish.expense_tracker.dto.request.UpdateMonthlyShareRequest;
import com.satish.expense_tracker.dto.response.BillingDateResponse;
import com.satish.expense_tracker.dto.response.MonthlyShareAmountResponse;
import com.satish.expense_tracker.dto.response.MonthlyShareResponse;
import com.satish.expense_tracker.entity.*;
import com.satish.expense_tracker.repository.BillingSettingRepository;
import com.satish.expense_tracker.repository.MonthlyShareRepository;
import com.satish.expense_tracker.repository.RoomRepository;
import com.satish.expense_tracker.repository.UserRepository;
import com.satish.expense_tracker.service.BillingSettingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BillingSettingServiceImpl
        implements BillingSettingService {

    private final BillingSettingRepository billingSettingRepository;

    private final MonthlyShareRepository monthlyShareRepository;

    private final UserRepository userRepository;

    private final RoomRepository roomRepository;

    @Override
    public void createMonthlyShareForUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found."));

        if (!"ACTIVE".equalsIgnoreCase(user.getStatus())) {
            return;
        }

        BillingSetting setting =
                billingSettingRepository.findAll()
                        .stream()
                        .findFirst()
                        .orElse(null);

        if (setting == null) {
            return;
        }

        int month = LocalDate.now().getMonthValue();
        int year = LocalDate.now().getYear();

        boolean exists = monthlyShareRepository
                .findByUserIdAndBillingMonthAndBillingYear(
                        userId,
                        month,
                        year
                )
                .isPresent();

        if (exists) {
            return;
        }

        MonthlyShare monthlyShare = MonthlyShare.builder()
                .user(user)
                .billingMonth(month)
                .billingYear(year)
                .monthlyShare(setting.getMonthlyShareAmount())
                .paidAmount(BigDecimal.ZERO)
                .dueAmount(setting.getMonthlyShareAmount())
                .status(ShareStatus.NOT_PAID)
                .createdDate(LocalDate.now())
                .build();

        monthlyShareRepository.save(monthlyShare);

    }

    @Override
    public BillingDateResponse getBillingDate() {

        BillingSetting setting =
                billingSettingRepository.findAll()
                        .stream()
                        .findFirst()
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Billing settings not found."
                                ));

        int billingStartDay = setting.getBillingStartDay();

        LocalDate today = LocalDate.now();

        LocalDate currentBillingStartDate;

        if (today.getDayOfMonth() >= billingStartDay) {

            currentBillingStartDate =
                    LocalDate.of(
                            today.getYear(),
                            today.getMonth(),
                            billingStartDay
                    );

        } else {

            LocalDate previousMonth =
                    today.minusMonths(1);

            int day = Math.min(
                    billingStartDay,
                    previousMonth.lengthOfMonth()
            );

            currentBillingStartDate =
                    LocalDate.of(
                            previousMonth.getYear(),
                            previousMonth.getMonth(),
                            day
                    );

        }

        LocalDate currentBillingEndDate =
                currentBillingStartDate
                        .plusMonths(1)
                        .minusDays(1);

        return BillingDateResponse.builder()
                .billingStartDay(billingStartDay)
                .currentBillingStartDate(
                        currentBillingStartDate.toString()
                )
                .currentBillingEndDate(
                        currentBillingEndDate.toString()
                )
                .build();

    }

    @Override
    public MonthlyShareAmountResponse getMonthlyShareAmount() {

        BillingSetting setting =
                billingSettingRepository.findAll()
                        .stream()
                        .findFirst()
                        .orElse(null);

        if (setting == null) {

            return MonthlyShareAmountResponse.builder()
                    .monthlyShareAmount(BigDecimal.ZERO)
                    .build();

        }

        return MonthlyShareAmountResponse.builder()
                .monthlyShareAmount(
                        setting.getMonthlyShareAmount()
                )
                .build();

    }

    @Override
    public BillingDateResponse updateBillingStartDay(
            Integer billingStartDay
    ) {

        BillingSetting setting =
                billingSettingRepository.findAll()
                        .stream()
                        .findFirst()
                        .orElse(null);

        if (setting == null) {

            Room room = roomRepository.findAll()
                    .stream()
                    .findFirst()
                    .orElseThrow(() ->
                            new RuntimeException("Room not found"));

            setting = BillingSetting.builder()
                    .room(room)
                    .updatedDate(LocalDate.now())
                    .build();

        }

        setting.setBillingStartDay(billingStartDay);

        setting.setUpdatedDate(LocalDate.now());

        billingSettingRepository.save(setting);

        return BillingDateResponse.builder()
                .billingStartDay(setting.getBillingStartDay())
                .build();

    }

    @Override
    public MonthlyShareAmountResponse updateMonthlyShareAmount(
            BigDecimal monthlyShareAmount
    ) {

        BillingSetting setting =
                billingSettingRepository.findAll()
                        .stream()
                        .findFirst()
                        .orElse(null);

        if (setting == null) {

            Room room = roomRepository.findAll()
                    .stream()
                    .findFirst()
                    .orElseThrow(() ->
                            new RuntimeException("Room not found"));

            setting = BillingSetting.builder()
                    .room(room)
                    .updatedDate(LocalDate.now())
                    .build();

        }

        setting.setMonthlyShareAmount(monthlyShareAmount);

        setting.setUpdatedDate(LocalDate.now());

        billingSettingRepository.save(setting);

        updateCurrentMonthShares(monthlyShareAmount);

        return MonthlyShareAmountResponse.builder()
                .monthlyShareAmount(setting.getMonthlyShareAmount())
                .build();

    }



    private void updateCurrentMonthShares(
            BigDecimal monthlyShareAmount
    ) {

        int month = LocalDate.now().getMonthValue();
        int year = LocalDate.now().getYear();

        List<User> users = userRepository.findAll();

        for (User user : users) {

            if (!"ACTIVE".equalsIgnoreCase(user.getStatus())) {
                continue;
            }

            MonthlyShare monthlyShare =
                    monthlyShareRepository
                            .findByUserIdAndBillingMonthAndBillingYear(
                                    user.getId(),
                                    month,
                                    year
                            )
                            .orElse(null);

            /*
             * New member
             */
            if (monthlyShare == null) {

                monthlyShare = MonthlyShare.builder()
                        .user(user)
                        .billingMonth(month)
                        .billingYear(year)
                        .monthlyShare(monthlyShareAmount)
                        .paidAmount(BigDecimal.ZERO)
                        .dueAmount(monthlyShareAmount)
                        .status(ShareStatus.NOT_PAID)
                        .createdDate(LocalDate.now())
                        .build();

            } else {

                /*
                 * Existing member
                 * Don't reset payment.
                 */

                monthlyShare.setMonthlyShare(monthlyShareAmount);

                BigDecimal paidAmount =
                        monthlyShare.getPaidAmount();

                BigDecimal dueAmount =
                        monthlyShareAmount.subtract(paidAmount);

                if (dueAmount.compareTo(BigDecimal.ZERO) < 0) {
                    dueAmount = BigDecimal.ZERO;
                }

                monthlyShare.setDueAmount(dueAmount);

                monthlyShare.setStatus(
                        calculateStatus(
                                paidAmount,
                                monthlyShareAmount
                        )
                );

            }

            monthlyShareRepository.save(monthlyShare);

        }

    }

    @Override
    public List<MonthlyShareResponse> getMonthlyShareCollection() {

        int month = LocalDate.now().getMonthValue();

        int year = LocalDate.now().getYear();

        return monthlyShareRepository
                .findByBillingMonthAndBillingYear(month, year)
                .stream()
                .map(monthlyShare -> MonthlyShareResponse.builder()
                        .id(monthlyShare.getId())
                        .userId(monthlyShare.getUser().getId())
                        .memberName(monthlyShare.getUser().getName())
                        .monthlyShare(monthlyShare.getMonthlyShare())
                        .paidAmount(monthlyShare.getPaidAmount())
                        .dueAmount(monthlyShare.getDueAmount())
                        .status(monthlyShare.getStatus().name())
                        .build())
                .toList();

    }

    @Override
    public void updateMonthlyShare(
            Long id,
            UpdateMonthlyShareRequest request
    ) {

        MonthlyShare monthlyShare =
                monthlyShareRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Monthly Share not found."
                                ));

        BigDecimal receivedAmount =
                request.getReceivedAmount();

        if (receivedAmount == null ||
                receivedAmount.compareTo(BigDecimal.ZERO) <= 0) {

            throw new RuntimeException(
                    "Received amount should be greater than zero."
            );

        }

        BigDecimal newPaidAmount =
                monthlyShare.getPaidAmount()
                        .add(receivedAmount);

        if (newPaidAmount.compareTo(
                monthlyShare.getMonthlyShare()) > 0) {

            throw new RuntimeException(
                    "Received amount exceeds monthly share."
            );

        }

        BigDecimal dueAmount =
                monthlyShare.getMonthlyShare()
                        .subtract(newPaidAmount);

        monthlyShare.setPaidAmount(newPaidAmount);

        monthlyShare.setDueAmount(dueAmount);

        monthlyShare.setStatus(
                calculateStatus(
                        newPaidAmount,
                        monthlyShare.getMonthlyShare()
                )
        );

        monthlyShareRepository.save(monthlyShare);

    }

    private ShareStatus calculateStatus(
            BigDecimal paid,
            BigDecimal total
    ) {

        if (paid.compareTo(BigDecimal.ZERO) == 0) {

            return ShareStatus.NOT_PAID;

        }

        if (paid.compareTo(total) >= 0) {

            return ShareStatus.PAID;

        }

        return ShareStatus.PARTIAL;

    }

}