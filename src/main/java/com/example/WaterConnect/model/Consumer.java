package com.example.WaterConnect.model;

import jakarta.persistence.*;

@Entity
public class Consumer extends User {

    private String fullName;
    private String address;

    public Consumer() {}

    public Consumer(Long id, String name, String email, String password, String phone,
                    String fullName, String address,String role) {
        super(id, name, email, password, phone,role);
        this.fullName = fullName;
        this.address = address;
    }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }


}