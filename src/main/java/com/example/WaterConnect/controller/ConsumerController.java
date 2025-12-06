package com.example.WaterConnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.WaterConnect.model.Consumer;
import com.example.WaterConnect.repository.ConsumerRepository;

@RestController
@RequestMapping("/consumers")
public class ConsumerController {

    @Autowired
    private ConsumerRepository repo;

    @PostMapping("/add")
    public Consumer add(@RequestBody Consumer c) {
        return repo.save(c);
    }

    @GetMapping("/all")
    public List<Consumer> all() {
        return repo.findAll();
    }
}