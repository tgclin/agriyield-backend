package com.example.demo.controller;

import com.example.demo.dto.AuthDtos.UserDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class HealthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/health")
    public ResponseEntity<?> checkSystemHealth() {
        try {
            long userCount = userRepository.count();
            return ResponseEntity.ok(Map.of(
                    "status", "UP",
                    "database", "Connected to PostgreSQL",
                    "totalUsersInDb", userCount
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "DOWN",
                    "database", "Connection Failed",
                    "error", e.getMessage()
            ));
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();

            List<UserDto> userDtos = users.stream()
                    .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(userDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "error", "Failed to retrieve users: " + e.getMessage()
            ));
        }
    }
}