package com.example.WaterConnect.service;

import java.util.Map;

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

public Object updateProfile(Long id, Object updates) {

    User user = userRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

    // Convert requestBody to Map
    Map<String, Object> map = (Map<String, Object>) updates;

    // UPDATE USER TABLE FIELDS
// UPDATE ONLY IF VALUES ARE PROVIDED
if (map.get("name") != null)
    user.setName((String) map.get("name"));

if (map.get("email") != null)
    user.setEmail((String) map.get("email"));

if (map.get("phone") != null)
    user.setPhone((String) map.get("phone"));

if (map.get("password") != null) {
    String newPassword = (String) map.get("password");
    user.setPassword(encoder.encode(newPassword));
}

    userRepo.save(user); // save basic user info

    // ROLE BASED UPDATE
    String role = user.getRole();

    // ---------- SUPPLIER ----------
    if ("SUPPLIER".equalsIgnoreCase(role)) {

        Supplier supplier = supplierRepo.findById(id)
                .orElse(new Supplier());

        supplier.setId(id);

        if (map.containsKey("companyName"))
            supplier.setCompanyName((String) map.get("companyName"));

        if (map.containsKey("serviceArea"))
            supplier.setServiceArea((String) map.get("serviceArea"));

        return supplierRepo.save(supplier);
    }

    // ---------- CONSUMER ----------
    if ("CONSUMER".equalsIgnoreCase(role)) {

        Consumer consumer = consumerRepo.findById(id)
                .orElse(new Consumer());

        consumer.setId(id);

        if (map.containsKey("fullname"))
            consumer.setFullName((String) map.get("fullname"));

        if (map.containsKey("address"))
            consumer.setAddress((String) map.get("address"));

        if (map.containsKey("phone"))
            consumer.setPhone((String) map.get("phone"));

        return consumerRepo.save(consumer);
    }

    return user;
}

    public Object getProfile(Long id) {
        return userRepo.findById(id).orElse(null);
    }
}
