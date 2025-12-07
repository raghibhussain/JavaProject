package com.example.WaterConnect.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Tanker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double capacity;
    private double price;
    private String type;

    private Long supplierId;

    public Tanker() {}

    public Tanker(Long id, double capacity, double price, String type, Long supplierId) {
        this.id = id;
        this.capacity = capacity;
        this.price = price;
        this.type = type;
        this.supplierId = supplierId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getCapacity() { return capacity; }
    public void setCapacity(double capacity) { this.capacity = capacity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }
}