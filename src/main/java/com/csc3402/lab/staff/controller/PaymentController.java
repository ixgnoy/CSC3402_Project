package com.csc3402.lab.staff.controller;
import com.csc3402.lab.staff.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.csc3402.lab.staff.model.Payment;
import com.csc3402.lab.staff.service.PaymentService;
import com.csc3402.lab.staff.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    private final BookingService bookingService;
    private final PaymentService paymentService;

    public PaymentController(BookingService bookingService, PaymentService paymentService) {
        this.bookingService = bookingService;
        this.paymentService = paymentService;
    }

    @GetMapping("list")
    public String showPaymentList(Model model) {
        model.addAttribute("payment", paymentService.listAllPayments());
        return "payment/list-payment";
    }
}
