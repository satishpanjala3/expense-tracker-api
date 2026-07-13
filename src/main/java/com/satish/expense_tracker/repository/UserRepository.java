package com.satish.expense_tracker.repository;


import com.satish.expense_tracker.entity.RequestStatus;
import com.satish.expense_tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    long countByStatus(String status);

    List<User> findByRequestStatus(RequestStatus requestStatus);

}