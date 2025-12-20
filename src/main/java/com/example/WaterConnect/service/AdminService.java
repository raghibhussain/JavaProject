package com.example.WaterConnect.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.WaterConnect.model.User;
import com.example.WaterConnect.repository.UserRepository;

@Service
public class AdminService {

    private final UserRepository userRepo;

    public AdminService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    // Get all users except admin
    public List<User> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .filter(user -> !"ADMIN".equals(user.getRole()))
                .toList();
    }

    // Delete user (admin protected)
    public void deleteUser(Long userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if ("ADMIN".equals(user.getRole())) {
            throw new RuntimeException("Admin cannot be deleted");
        }

        userRepo.deleteById(userId);
    }
}
