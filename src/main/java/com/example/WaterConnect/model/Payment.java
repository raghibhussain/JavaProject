package com.example.WaterConnect.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookingId;
    private double bookingAmount;
    private String paymentDate;
    private String status;

    public Payment() {}

    public Payment(Long id, Long bookingId, double bookingAmount, String paymentDate, String status) {
        this.id = id;
        this.bookingId = bookingId;
        this.bookingAmount = bookingAmount;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }

    public double getBookingAmount() { return bookingAmount; }
    public void setBookingAmount(double bookingAmount) { this.bookingAmount = bookingAmount; }

    public String getPaymentDate() { return paymentDate; }
    public void setPaymentDate(String paymentDate) { this.paymentDate = paymentDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}