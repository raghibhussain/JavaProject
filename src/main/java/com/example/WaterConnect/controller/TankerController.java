package com.example.WaterConnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.WaterConnect.model.Tanker;
import com.example.WaterConnect.repository.TankerRepository;

@RestController
@RequestMapping("/tankers")
public class TankerController {

    @Autowired
    private TankerRepository repo;

    @PostMapping("/add")
    public Tanker add(@RequestBody Tanker t) {
        return repo.save(t);
    }

    @GetMapping("/all")
    public List<Tanker> all() {
        return repo.findAll();
    }
}