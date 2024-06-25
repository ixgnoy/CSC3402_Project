package com.csc3402.lab.staff.repository;

import com.csc3402.lab.staff.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Booking findBookingByBookingId(Integer bookingId);

}
