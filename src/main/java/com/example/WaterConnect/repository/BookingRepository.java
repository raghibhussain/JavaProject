package com.example.WaterConnect.repository;

import com.example.WaterConnect.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByConsumer_Id(Long consumerId);

    List<Booking> findBySupplier_Id(Long supplierId);

    Optional<Booking> findByIdAndSupplier_Id(Long bookingId, Long supplierId);
}
