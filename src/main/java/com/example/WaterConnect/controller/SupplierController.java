package com.example.WaterConnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.WaterConnect.model.Supplier;
import com.example.WaterConnect.repository.SupplierRepository;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierRepository repo;

    @PostMapping("/add")
    public Supplier add(@RequestBody Supplier s) {
        return repo.save(s);
    }

    @GetMapping("/all")
    public List<Supplier> all() {
        return repo.findAll();
    }
}