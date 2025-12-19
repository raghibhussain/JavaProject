package com.example.WaterConnect.repository;

import com.example.WaterConnect.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    List<Supplier> findByCompanyNameContainingIgnoreCase(String name);

    List<Supplier> findByServiceAreaContainingIgnoreCase(String area);
}
