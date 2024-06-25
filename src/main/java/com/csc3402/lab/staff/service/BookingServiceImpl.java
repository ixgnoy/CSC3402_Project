package com.csc3402.lab.staff.service;

import com.csc3402.lab.staff.model.Booking;
import com.csc3402.lab.staff.model.Package;
import com.csc3402.lab.staff.repository.BookingRepository;
import com.csc3402.lab.staff.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final PackageRepository packageRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, PackageRepository packageRepository) {
        this.bookingRepository = bookingRepository;
        this.packageRepository = packageRepository;
    }
    @Override
    public List<Booking> listAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking addNewBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Optional<Booking> findBookingById(Integer bookingId) {
        return bookingRepository.findById(bookingId);
    }

    @Override
    public Booking updateBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(Booking booking) {
        bookingRepository.delete(booking);
    }

    @Override
    public Booking createBooking(String destination, String name, String email, String phone, Integer pax, LocalDate startDate, LocalDate endDate, Integer packageId) {
        Package apackage = packageRepository.findById(packageId).orElseThrow(() -> new IllegalArgumentException("Invalid package ID"));
        Booking booking = new Booking(destination, name, email, phone, pax, startDate, endDate, apackage);
        return bookingRepository.save(booking);
    }

}
