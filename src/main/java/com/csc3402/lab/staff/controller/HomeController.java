package com.csc3402.lab.staff.controller;

import com.csc3402.lab.staff.model.Payment;
import com.csc3402.lab.staff.model.User;
import com.csc3402.lab.staff.service.PaymentService;
import com.csc3402.lab.staff.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.csc3402.lab.staff.model.Booking;
import com.csc3402.lab.staff.service.BookingService;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class HomeController {
    private final BookingService bookingService;
    private final PaymentService paymentService;

    private final UserService userService;

    public HomeController(UserService userService, BookingService bookingService, PaymentService paymentService) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.paymentService = paymentService;
    }

    @PostMapping("add")
    public String addUser(@Valid User user, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return "User/add-user";  // Redirect back to the user addition form if there are validation errors
        }
        User savedUser = userService.addNewUser(user);
        if (savedUser != null && savedUser.getUserId() != null) {
            session.setAttribute("savedUserId", savedUser.getUserId());  // Save user ID in session
        } else {
            model.addAttribute("error", "Failed to create user. Please try again.");
            return "User/add-user";  // Redirect back to user addition page with error
        }
        return "redirect:/LogIn.html";  // Redirect to the login page after successful registration
    }


    @PostMapping("payment")
    public String addPayment(@RequestParam("creditCardName") String creditCardName,
                             @RequestParam("creditCardNumber") String creditCardNumber,
                             @RequestParam("expiryDate") String expiryDate,
                             @RequestParam("cvv") String cvv,
                             HttpSession session,  // Use HttpSession to retrieve the booking ID
                             Model model) {
        Integer bookingId = (Integer) session.getAttribute("savedBookingId");  // Retrieve booking ID from session
        if (bookingId == null) {
            throw new IllegalArgumentException("Booking ID is required and not found in session.");
        }
        Payment payment = paymentService.addNewPayment(creditCardName, creditCardNumber, expiryDate, cvv, bookingId);
        model.addAttribute("payments", paymentService.listAllPayments());
        return "redirect:/EndingPage.html";  // Redirect to the ending page after payment
    }
    @PostMapping("create")
    public String createBooking(@RequestParam("destination") String destination,
                                @RequestParam("name") String name,
                                @RequestParam("email") String email,
                                @RequestParam("phone") String phone,
                                @RequestParam("pax") Integer pax,
                                @RequestParam("startDate") String startDate,
                                @RequestParam("endDate") String endDate,
                                @RequestParam("packageId") Integer packageId,
                                Model model, HttpSession session) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        Booking booking = bookingService.createBooking(destination, name, email, phone, pax, start, end, packageId);

        if (booking != null && booking.getBookingId() != null) {
            session.setAttribute("savedBookingId", booking.getBookingId());
            Integer userId = (Integer) session.getAttribute("savedUserId");
            if (userId != null) {
                Optional<User> userOptional = userService.findUserById(userId);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    booking.setUser(user);
                    bookingService.updateBooking(booking);
                } else {
                    model.addAttribute("error", "No user found with ID: " + userId);
                    return "Booking/add-booking";
                }
            }
            model.addAttribute("bookings", bookingService.listAllBookings());
        } else {
            model.addAttribute("error", "Failed to create booking. Please try again.");
            return "Booking/add-booking";
        }

        return "redirect:/checkout.html";
    }


}