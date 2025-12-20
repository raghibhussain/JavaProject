package com.example.WaterConnect.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Consumer extends User {

    private String fullName;
    private String address;

    public Consumer() {}

    public Consumer(Long id, String name, String email, String password, String phone,
                    String fullName, String address, String role) {
        super(id, name, email, password, phone, role);
        this.fullName = fullName;
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL)
    private List<Booking> bookings;
}
