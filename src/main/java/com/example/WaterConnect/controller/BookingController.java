package com.example.WaterConnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.WaterConnect.model.Booking;
import com.example.WaterConnect.repository.BookingRepository;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingRepository repo;

    @PostMapping("/create")
    public Booking create(@RequestBody Booking b) {
        b.setStatus("Pending");
        return repo.save(b);
    }

    @GetMapping("/all")
    public List<Booking> all() {
        return repo.findAll();
    }
}