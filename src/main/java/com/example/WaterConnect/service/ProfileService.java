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

    @SuppressWarnings("unchecked")
    public Object updateProfile(Long id, Object updates) {

        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Map<String, Object> map = (Map<String, Object>) updates;

        /* ================= USER TABLE ================= */

        if (hasValue(map.get("name")))
            user.setName(map.get("name").toString());

        if (hasValue(map.get("email")))
            user.setEmail(map.get("email").toString());

        if (hasValue(map.get("phone")))
            user.setPhone(map.get("phone").toString());

        if (hasValue(map.get("password"))) {
            user.setPassword(encoder.encode(map.get("password").toString()));
        }

        userRepo.save(user);

        /* ================= ROLE TABLE ================= */

        Object roleData = null;

        if ("SUPPLIER".equalsIgnoreCase(user.getRole())) {

            Supplier supplier = supplierRepo.findById(id)
                    .orElseGet(() -> {
                        Supplier s = new Supplier();
                        s.setId(id);
                        return s;
                    });

            if (hasValue(map.get("companyName")))
                supplier.setCompanyName(map.get("companyName").toString());

            if (hasValue(map.get("serviceArea")))
                supplier.setServiceArea(map.get("serviceArea").toString());

            roleData = supplierRepo.save(supplier);
        }

        if ("CONSUMER".equalsIgnoreCase(user.getRole())) {

            Consumer consumer = consumerRepo.findById(id)
                    .orElseGet(() -> {
                        Consumer c = new Consumer();
                        c.setId(id);
                        return c;
                    });

            if (hasValue(map.get("fullname")))
                consumer.setFullName(map.get("fullname").toString());

            if (hasValue(map.get("address")))
                consumer.setAddress(map.get("address").toString());

            roleData = consumerRepo.save(consumer);
        }

        /* ================= RESPONSE ================= */

        return buildProfileResponse(user, roleData);
    }

    public Object getProfile(Long id) {
        User user = userRepo.findById(id).orElse(null);
        if (user == null) return null;

        Object roleData = null;

        if ("SUPPLIER".equalsIgnoreCase(user.getRole()))
            roleData = supplierRepo.findById(id).orElse(null);

        if ("CONSUMER".equalsIgnoreCase(user.getRole()))
            roleData = consumerRepo.findById(id).orElse(null);

        return buildProfileResponse(user, roleData);
    }

    /* ================= HELPERS ================= */

    private boolean hasValue(Object value) {
        return value != null && !value.toString().trim().isEmpty();
    }

    private Map<String, Object> buildProfileResponse(User user, Object roleData) {
        Map<String, Object> response = new java.util.HashMap<>();

        response.put("id", user.getId());
        response.put("name", user.getName());
        response.put("email", user.getEmail());
        response.put("phone", user.getPhone());
        response.put("role", user.getRole());

        if (roleData instanceof Consumer consumer) {
            response.put("fullname", consumer.getFullName());
            response.put("address", consumer.getAddress());
        }

        if (roleData instanceof Supplier supplier) {
            response.put("companyName", supplier.getCompanyName());
            response.put("serviceArea", supplier.getServiceArea());
        }

        return response;
    }
}
