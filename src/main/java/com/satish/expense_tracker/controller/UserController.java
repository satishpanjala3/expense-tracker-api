package com.satish.expense_tracker.controller;

import com.satish.expense_tracker.dto.request.AddUserRequest;
import com.satish.expense_tracker.dto.request.UpdateUserRequest;
import com.satish.expense_tracker.dto.response.UserResponse;
import com.satish.expense_tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://expense-tracker-gamma-rouge.vercel.app")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserResponse> getAllUsers() {

        return userService.getAllUsers();

    }

    @GetMapping("/{id}")
    public UserResponse getUser(
            @PathVariable Long id) {

        return userService.getUser(id);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse addUser(
            @RequestBody AddUserRequest request) {

        return userService.addUser(request);

    }

    @PutMapping("/{id}")
    public UserResponse updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest request) {

        return userService.updateUser(id, request);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(
            @PathVariable Long id) {

        userService.deleteUser(id);

    }

}
