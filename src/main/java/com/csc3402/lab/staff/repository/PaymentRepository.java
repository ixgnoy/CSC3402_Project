package com.csc3402.lab.staff.repository;

import com.csc3402.lab.staff.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
        @Query("SELECT p FROM Payment p WHERE p.booking.bookingId = :bookingId")
        Payment findPaymentByBookingId(@Param("bookingId") Integer bookingId);
    }
