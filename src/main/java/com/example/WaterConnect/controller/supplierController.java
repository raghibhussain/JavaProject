package com.example.WaterConnect.controller;

import com.example.WaterConnect.model.Supplier;
import com.example.WaterConnect.repository.SupplierRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
@CrossOrigin(origins = "*")
public class SupplierController {

    private final SupplierRepository supplierRepository;

    public SupplierController(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }


    @GetMapping("/search/area/{area}")
    public List<Supplier> searchByArea(@PathVariable String area) {
        return supplierRepository.findByServiceAreaContainingIgnoreCase(area);
    }
}


