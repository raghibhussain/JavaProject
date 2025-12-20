package com.example.WaterConnect.repository;

import com.example.WaterConnect.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByConsumerId(Long consumerId);

    List<Booking> findBySupplierId(Long supplierId);

    Optional<Booking> findByIdAndSupplierId(Long bookingId, Long supplierId);
}