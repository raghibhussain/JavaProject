package com.example.WaterConnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.WaterConnect.model.Payment;
import com.example.WaterConnect.repository.PaymentRepository;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentRepository repo;

    @PostMapping("/process")
    public Payment pay(@RequestBody Payment p) {
        p.setStatus("Paid");
        return repo.save(p);
    }

    @GetMapping("/all")
    public List<Payment> all() {
        return repo.findAll();
    }
}