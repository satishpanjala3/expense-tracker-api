package com.satish.expense_tracker.service;

import com.satish.expense_tracker.dto.response.UserRequestResponse;

import java.util.List;

public interface UserRequestService {

    List<UserRequestResponse> getPendingRequests();

    void approveRequest(Long id);

    void rejectRequest(Long id);

}