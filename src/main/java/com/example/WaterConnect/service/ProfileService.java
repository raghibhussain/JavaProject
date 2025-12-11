package com.example.WaterConnect.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.WaterConnect.model.Consumer;
import com.example.WaterConnect.model.Supplier;
import com.example.WaterConnect.model.User;
import com.example.WaterConnect.repository.ConsumerRepository;
import com.example.WaterConnect.repository.SupplierRepository;
import com.example.WaterConnect.repository.UserRepository;

@Service
public class ProfileService {

    private final UserRepository userRepo;
    private final SupplierRepository supplierRepo;
    private final ConsumerRepository consumerRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public ProfileService(UserRepository userRepo,
                          SupplierRepository supplierRepo,
                          ConsumerRepository consumerRepo) {

        this.userRepo = userRepo;
        this.supplierRepo = supplierRepo;
        this.consumerRepo = consumerRepo;
    }

    public Object updateProfile(Long id, User updatedData) {

        User existing = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // update shared user fields
        existing.setName(updatedData.getName());
        existing.setEmail(updatedData.getEmail());
        existing.setPhone(updatedData.getPhone());

        if (updatedData.getPassword() != null && !updatedData.getPassword().isEmpty()) {
            existing.setPassword(encoder.encode(updatedData.getPassword()));
        }

        userRepo.save(existing);

        // If SUPPLIER
        if ("SUPPLIER".equalsIgnoreCase(existing.getRole())) {

            Supplier supplier = supplierRepo.findById(id)
                    .orElse(new Supplier());

            supplier.setId(id);
            supplier.setCompanyName(((Supplier) updatedData).getCompanyName());
            supplier.setServiceArea(((Supplier) updatedData).getServiceArea());

            return supplierRepo.save(supplier);
        }

        // If CONSUMER
        if ("CONSUMER".equalsIgnoreCase(existing.getRole())) {

            Consumer consumer = consumerRepo.findById(id)
                    .orElse(new Consumer());

            consumer.setId(id);
            consumer.setFullName(((Consumer) updatedData).getFullName());
            consumer.setAddress(((Consumer) updatedData).getAddress());

            return consumerRepo.save(consumer);
        }

        return existing;
    }

    public Object getProfile(Long id) {
        return userRepo.findById(id).orElse(null);
    }
}