package com.csc3402.lab.staff.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PaymentID")
    private Integer paymentId;

    @Column(name = "CreditCardName")
    private String creditCardName;

    @Column(name = "CreditNumber")
    private String creditCardNumber;

    @Column(name = "ExpiryDate")
    private String expiryDate;

    @Column(name = "CVV")
    private String cvv;

    @OneToOne (optional = false)
    @JoinColumn(name = "Booking_id", nullable = false )
    private Booking booking;

    // Getters and setters
    public Payment() {
    }
    public Payment(String creditCardName, String creditCardNumber, String expiryDate, String cvv, Booking booking) {
        this.creditCardName = creditCardName;
        this.creditCardNumber = creditCardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.booking = booking;
    }
    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public String getCreditCardName() {
        return creditCardName;
    }

    public void setCreditCardName(String creditCardName) {
        this.creditCardName = creditCardName;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", creditCardName='" + creditCardName + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", cvv='" + cvv + '\'' +
                ", booking=" + booking +
                '}';
    }
}
