package com.csc3402.lab.staff.service;
import com.csc3402.lab.staff.model.Booking;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface BookingService {
    List<Booking> listAllBookings();
    Booking addNewBooking(Booking booking);
    Optional<Booking> findBookingById(Integer bookingId);
    Booking updateBooking(Booking booking);
    void deleteBooking(Booking booking);

    Booking createBooking(String destination, String name, String email, String phone, Integer pax, LocalDate startDate, LocalDate endDate, Integer packageId);

}