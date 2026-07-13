package com.satish.expense_tracker.controller;

import com.satish.expense_tracker.dto.response.UserRequestResponse;
import com.satish.expense_tracker.service.UserRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class UserRequestController {

    private final UserRequestService service;

    @GetMapping
    public ResponseEntity<List<UserRequestResponse>> getRequests() {

        return ResponseEntity.ok(
                service.getPendingRequests());

    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<String> approve(
            @PathVariable Long id) {

        service.approveRequest(id);

        return ResponseEntity.ok("Approved");

    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<String> reject(
            @PathVariable Long id) {

        service.rejectRequest(id);

        return ResponseEntity.ok("Rejected");

    }

}