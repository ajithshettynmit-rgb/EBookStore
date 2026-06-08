package com.ebookstore.controller;

import com.ebookstore.entity.User;
import com.ebookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(@RequestParam Long userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }
}

// Made with Bob
