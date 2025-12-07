package com.example.WaterConnect.controller;

import com.example.WaterConnect.model.Consumer;
import com.example.WaterConnect.repository.ConsumerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumers")
public class ConsumerController {

    private final ConsumerRepository repo;

    public ConsumerController(ConsumerRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Consumer create(@RequestBody Consumer consumer) {
        return repo.save(consumer);
    }

    @GetMapping
    public List<Consumer> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Consumer getById(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Consumer update(@PathVariable Long id, @RequestBody Consumer c) {
        c.setId(id);
        return repo.save(c);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}