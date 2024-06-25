package com.csc3402.lab.staff.service;


import com.csc3402.lab.staff.model.Booking;
import com.csc3402.lab.staff.model.Payment;
import com.csc3402.lab.staff.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PaymentServiceImpl implements PaymentService{
    private final PaymentRepository paymentRepository;
    private final BookingService bookingService;

    public PaymentServiceImpl(PaymentRepository paymentRepository, BookingService bookingService) {
        this.paymentRepository = paymentRepository;
        this.bookingService = bookingService;
    }


    @Override
    public List<Payment> listAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public List<Payment> findPaymentByUserId(Integer id) {
        return null;
    }

    @Override
    public Payment addNewPayment(String creditCardName, String creditCardNumber, String expiryDate, String cvv, Integer bookingId) {
        Booking booking = bookingService.findBookingById(bookingId).orElseThrow(() -> new IllegalArgumentException("Invalid booking ID"));
        Payment payment = new Payment(creditCardName, creditCardNumber, expiryDate, cvv, booking);
        return paymentRepository.save(payment);
    }
    public Payment findPaymentById(Integer id) {
        return (Payment) paymentRepository.findPaymentByBookingId(id);
    }
}
