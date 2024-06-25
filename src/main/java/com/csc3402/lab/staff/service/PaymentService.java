package com.csc3402.lab.staff.service;
import com.csc3402.lab.staff.model.Payment;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PaymentService{

        List<Payment> listAllPayments();
        List<Payment> findPaymentByUserId(Integer id);

        Payment addNewPayment(String creditCardName, String creditCardNumber, String expiryDate, String cvv, Integer bookingId);
}
