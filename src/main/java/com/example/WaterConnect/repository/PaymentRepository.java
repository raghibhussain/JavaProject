package com.example.WaterConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.WaterConnect.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {}