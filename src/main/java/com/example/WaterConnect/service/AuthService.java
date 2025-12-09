package com.example.WaterConnect.service;

import com.example.WaterConnect.model.*;
import com.example.WaterConnect.repository.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepo;
    private final SupplierRepository supplierRepo;
    private final ConsumerRepository consumerRepo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepo,
                       SupplierRepository supplierRepo,
                       ConsumerRepository consumerRepo) {
        this.userRepo = userRepo;
        this.supplierRepo = supplierRepo;
        this.consumerRepo = consumerRepo;
    }

    // SIGNUP
    public User registerUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public Supplier registerSupplier(Supplier supplier) {
        supplier.setPassword(encoder.encode(supplier.getPassword()));
        supplier.setRole("SUPPLIER");
        return supplierRepo.save(supplier);
    }

    public Consumer registerConsumer(Consumer consumer) {
        consumer.setPassword(encoder.encode(consumer.getPassword()));
        consumer.setRole("CONSUMER");
        return consumerRepo.save(consumer);
    }

    // LOGIN
    public User login(String email, String password) {
        User user = userRepo.findByEmail(email);
        if (user == null) return null;

        if (encoder.matches(password, user.getPassword()))
            return user;

        return null;
    }
}
