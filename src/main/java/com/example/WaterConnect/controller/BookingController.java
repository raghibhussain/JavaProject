package com.example.WaterConnect.controller;

import com.example.WaterConnect.model.Booking;
import com.example.WaterConnect.repository.BookingRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingRepository repo;

    public BookingController(BookingRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Booking create(@RequestBody Booking booking) {
        return repo.save(booking);
    }

    @GetMapping
    public List<Booking> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Booking getById(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Booking update(@PathVariable Long id, @RequestBody Booking b) {
        b.setId(id);
        return repo.save(b);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}