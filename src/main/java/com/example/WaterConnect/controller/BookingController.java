package com.example.WaterConnect.controller;

import com.example.WaterConnect.model.Booking;
import com.example.WaterConnect.service.BookingService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
@CrossOrigin("*")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // ---------------- CREATE BOOKING (Consumer) ----------------
    @PostMapping("/create")
    public Booking createBooking(
            @RequestParam Long consumerId,
            @RequestParam Long supplierId,
            @RequestBody Booking booking) {

        return bookingService.createBooking(consumerId, supplierId, booking);
    }

    // ---------------- ACCEPT BOOKING (Supplier) ----------------
    @PutMapping("/{bookingId}/accept")
    public Booking acceptBooking(
            @PathVariable Long bookingId,
            @RequestParam Long supplierId) {

        return bookingService.acceptBooking(bookingId, supplierId);
    }

    // ---------------- REJECT BOOKING (Supplier) ----------------
    @PutMapping("/{bookingId}/reject")
    public Booking rejectBooking(
            @PathVariable Long bookingId,
            @RequestParam Long supplierId) {

        return bookingService.rejectBooking(bookingId, supplierId);
    }

    // ---------------- COMPLETE BOOKING (Supplier) ----------------
    @PutMapping("/{bookingId}/complete")
    public Booking completeBooking(
            @PathVariable Long bookingId,
            @RequestParam Long supplierId) {

        return bookingService.completeBooking(bookingId, supplierId);
    }
    // ---------------- GET BOOKINGS BY CONSUMER ----------------
@GetMapping("/consumer/{consumerId}")
public List<Booking> getBookingsByConsumer(@PathVariable Long consumerId) {
    return bookingService.getBookingsByConsumer(consumerId);
}

// ---------------- GET BOOKINGS BY SUPPLIER ----------------
@GetMapping("/supplier/{supplierId}")
public List<Booking> getBookingsBySupplier(@PathVariable Long supplierId) {
    return bookingService.getBookingsBySupplier(supplierId);
}

}