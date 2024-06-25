package com.csc3402.lab.staff.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "Bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Booking_id")
    private Integer bookingId;

    @Column(name = "Destination")
    private String destination;

    @Column(name = "Name")
    private String name;

    @Column(name = "Email")
    private String email;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Pax")
    private Integer pax;

    @Column(name = "Start_date")
    private LocalDate startDate;

    @Column(name = "End_date")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "package_id", nullable = false)
    private Package apackage;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private Payment payment;

    public Booking() {
    }

    public Booking(String destination, String name, String email, String phone, Integer pax, LocalDate startDate, LocalDate endDate, Package apackage) {
        this.destination = destination;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.pax = pax;
        this.startDate = startDate;
        this.endDate = endDate;
        this.apackage = apackage;
    }

    // Getters and setters

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getPax() {
        return pax;
    }

    public void setPax(Integer pax) {
        this.pax = pax;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Package getPackage() {
        return apackage;
    }

    public void setPackage(Package apackage) {
        this.apackage = apackage;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
        payment.setBooking(this);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", destination='" + destination + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", pax=" + pax +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", user=" + user +
                ", apackage=" + apackage +
                ", payment=" + payment +
                '}';
    }
}
