package com.example.WaterConnect.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Supplier extends User {

    private String companyName;
    private String serviceArea;

    public Supplier() {}

    public Supplier(Long id, String name, String email, String password, String phone,
                    String companyName, String serviceArea, String role) {
        super(id, name, email, password, phone, role);
        this.companyName = companyName;
        this.serviceArea = serviceArea;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getServiceArea() {
        return serviceArea;
    }

    public void setServiceArea(String serviceArea) {
        this.serviceArea = serviceArea;
    }

    // ONE supplier -> MANY bookings
    @JsonIgnore
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Booking> bookings;
}

