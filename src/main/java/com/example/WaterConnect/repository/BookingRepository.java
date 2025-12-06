package com.example.WaterConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.WaterConnect.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {}