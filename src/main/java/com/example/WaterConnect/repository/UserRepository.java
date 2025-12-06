package com.example.WaterConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.WaterConnect.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}