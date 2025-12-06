package com.example.WaterConnect.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long consumerId;
    private Long supplierId;
    private Long tankerId;
    private String date;
    private String status;

    public Long getConsumerId() {
        return consumerId;
    }
    public void setConsumerId(Long consumerId) {
        this.consumerId = consumerId;
    }
    public Long getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }
    public Long getTankerId() {
        return tankerId;
    }
    public void setTankerId(Long tankerId) {
        this.tankerId = tankerId;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Booking() {}
}