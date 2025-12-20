package com.example.WaterConnect.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.WaterConnect.model.User;
import com.example.WaterConnect.repository.UserRepository;

@Component
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AdminInitializer(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void run(String... args) {

        String adminEmail = "admin@admin.com";

        User existingAdmin = userRepo.findByEmail(adminEmail);

           if (existingAdmin == null) {
           
               User admin = new User();
               admin.setName("Admin");
               admin.setEmail(adminEmail);
               admin.setPassword(encoder.encode("admin123"));
               admin.setPhone("0000000000");
               admin.setRole("ADMIN");
           
               userRepo.save(admin);
           
               System.out.println("✅ ADMIN user created");
           } else {
               System.out.println("ℹ ADMIN already exists");
           }

    }
}
