package com.example.WaterConnect.service;

import org.springframework.stereotype.Service;

import com.example.WaterConnect.model.Booking;
import com.example.WaterConnect.model.Consumer;
import com.example.WaterConnect.model.Supplier;
import com.example.WaterConnect.repository.BookingRepository;
import com.example.WaterConnect.repository.ConsumerRepository;
import com.example.WaterConnect.repository.SupplierRepository;
import java.util.List;


@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ConsumerRepository consumerRepository;
    private final SupplierRepository supplierRepository;

    public BookingService(
            BookingRepository bookingRepository,
            ConsumerRepository consumerRepository,
            SupplierRepository supplierRepository) {
        this.bookingRepository = bookingRepository;
        this.consumerRepository = consumerRepository;
        this.supplierRepository = supplierRepository;
    }

    // ---------------- CREATE BOOKING ----------------
    public Booking createBooking(Long consumerId, Long supplierId, Booking booking) {

        Consumer consumer = consumerRepository.findById(consumerId)
                .orElseThrow(() -> new RuntimeException("Consumer not found"));

        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        booking.setConsumer(consumer);
        booking.setSupplier(supplier);
        booking.setStatus("PENDING");

        return bookingRepository.save(booking);
    }

    // ---------------- ACCEPT BOOKING ----------------
    public Booking acceptBooking(Long bookingId, Long supplierId) {

        Booking booking = bookingRepository
                .findByIdAndSupplierId(bookingId, supplierId)
                .orElseThrow(() -> new RuntimeException("Booking not found for this supplier"));

        if (!"PENDING".equals(booking.getStatus())) {
            throw new RuntimeException("Only PENDING bookings can be accepted");
        }

        booking.setStatus("ACCEPTED");
        return bookingRepository.save(booking);
    }

    // ---------------- REJECT BOOKING ----------------
    public Booking rejectBooking(Long bookingId, Long supplierId) {

        Booking booking = bookingRepository
                .findByIdAndSupplierId(bookingId, supplierId)
                .orElseThrow(() -> new RuntimeException("Booking not found for this supplier"));

        if (!"PENDING".equals(booking.getStatus())) {
            throw new RuntimeException("Only PENDING bookings can be rejected");
        }

        booking.setStatus("REJECTED");
        return bookingRepository.save(booking);
    }

    // ---------------- COMPLETE BOOKING ----------------
    public Booking completeBooking(Long bookingId, Long supplierId) {

        Booking booking = bookingRepository
                .findByIdAndSupplierId(bookingId, supplierId)
                .orElseThrow(() -> new RuntimeException("Booking not found for this supplier"));

        if (!"ACCEPTED".equals(booking.getStatus())) {
            throw new RuntimeException("Only ACCEPTED bookings can be completed");
        }

        booking.setStatus("COMPLETED");
        return bookingRepository.save(booking);
    }
    public List<Booking> getBookingsByConsumer(Long consumerId) {
    return bookingRepository.findByConsumerId(consumerId);
}

public List<Booking> getBookingsBySupplier(Long supplierId) {
    return bookingRepository.findBySupplierId(supplierId);
}

}