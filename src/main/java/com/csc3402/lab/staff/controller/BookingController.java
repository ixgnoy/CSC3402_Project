package com.csc3402.lab.staff.controller;

import com.csc3402.lab.staff.model.Booking;
import com.csc3402.lab.staff.service.BookingService;
import com.csc3402.lab.staff.service.PackageService;
import com.csc3402.lab.staff.service.PaymentService;
import com.csc3402.lab.staff.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;
    private final PaymentService paymentService;
    private final UserService userService;
    private final PackageService packageService;

    public BookingController(BookingService bookingService, PaymentService paymentService, UserService userService, PackageService packageService) {
        this.bookingService = bookingService;
        this.paymentService = paymentService;
        this.userService = userService;
        this.packageService = packageService;
    }

    @GetMapping("list")
    public String showBookingList(Model model) {
        model.addAttribute("bookings", bookingService.listAllBookings());
        return "Booking/list-booking";
    }

    @GetMapping("update")
    public String showUpdateMainForm(Model model) {
        model.addAttribute("bookings", bookingService.listAllBookings());
        return "Booking/choose-booking-to-update";
    }

    @GetMapping("edit/{id}")
    public String showUpdateBookingForm(@PathVariable("id") Integer id, Model model) {
        Booking booking = bookingService.findBookingById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid booking Id:" + id));
        model.addAttribute("users", userService.listAllUsers());
        model.addAttribute("packages", packageService.listAllPackages());
        model.addAttribute("payments", paymentService.listAllPayments());
        model.addAttribute("booking", booking);
        return "Booking/update-booking";
    }
        @PostMapping("update/{id}")
        public String updateBooking ( @PathVariable("id") Integer id, @Valid Booking booking, BindingResult result, Model
        model){
            if (result.hasErrors()) {
                booking.setBookingId(id);
                return "index";
            }
            bookingService.updateBooking(booking);
            model.addAttribute("booking", bookingService.listAllBookings());
            return "redirect:/booking/list";
        }

        @GetMapping("delete")
        public String showDeleteBookingForm (Model model){
            model.addAttribute("bookings", bookingService.listAllBookings());
            return "Booking/delete-booking";
        }

        @GetMapping("delete/{id}")
        public String deleteBooking ( @PathVariable("id") Integer id, Model model) {
            Booking booking = bookingService.findBookingById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid booking Id:" + id));
            bookingService.deleteBooking(booking);
            model.addAttribute("bookings", bookingService.listAllBookings());
            return "redirect:/booking/list";

        }
    }
