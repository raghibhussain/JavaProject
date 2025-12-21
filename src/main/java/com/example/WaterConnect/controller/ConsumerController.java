package com.example.WaterConnect.controller;

import com.example.WaterConnect.model.Consumer;
import com.example.WaterConnect.repository.ConsumerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumers")
public class ConsumerController {

    @Autowired
    private ConsumerRepository consumerRepo;

    @GetMapping("/search/name/{name}")
    public List<Consumer> searchByName(@PathVariable String name) {
        return consumerRepo.findByFullNameContainingIgnoreCase(name);
    }

    @GetMapping("/search/address/{address}")
    public List<Consumer> searchByAddress(@PathVariable String address) {
        return consumerRepo.findByAddressContainingIgnoreCase(address);
    }

    // ⭐ Recommendation (Supplier sees nearby consumers)
    @GetMapping("/recommend")
    public List<Consumer> recommendConsumers(@RequestParam String area) {
        return consumerRepo.findByAddressContainingIgnoreCase(area);
    }
}
