package com.example.WaterConnect.controller;

import com.example.WaterConnect.model.Supplier;
import com.example.WaterConnect.repository.SupplierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class supplierController {

    @Autowired
    private SupplierRepository supplierRepo;

    @GetMapping("/search/name/{name}")
    public List<Supplier> searchByName(@PathVariable String name) {
        return supplierRepo.findByCompanyNameContainingIgnoreCase(name);
    }

    @GetMapping("/search/area/{area}")
    public List<Supplier> searchByArea(@PathVariable String area) {
        return supplierRepo.findByServiceAreaContainingIgnoreCase(area);
    }
}


