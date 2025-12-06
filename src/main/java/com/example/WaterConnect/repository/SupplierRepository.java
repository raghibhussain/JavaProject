package com.example.WaterConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.WaterConnect.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {}