package com.example.WaterConnect.controller;

import com.example.WaterConnect.model.Tanker;
import com.example.WaterConnect.repository.TankerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tankers")
public class TankerController {

    private final TankerRepository repo;

    public TankerController(TankerRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Tanker create(@RequestBody Tanker tanker) {
        return repo.save(tanker);
    }

    @GetMapping
    public List<Tanker> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Tanker getById(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Tanker update(@PathVariable Long id, @RequestBody Tanker t) {
        t.setId(id);
        return repo.save(t);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}