package com.csc3402.lab.staff.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Package")
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PackageID")
    private Integer packageId;

    @Column(name = "package_name")
    private String packageName;

   @Column(name = "rating")
    private Integer rating;

   @Column(name = "Duration")
    private Integer duration;

   @Column(name = "price")
    private Integer price;

    @OneToMany(mappedBy = "apackage", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Booking> bookings = new HashSet<>();

    public Package() {
    }

    public Package(String packageName, Integer rating, Integer duration, Integer price) {
        this.packageName = packageName;
        this.rating = rating;
        this.duration = duration;
        this.price = price;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Package{" +
                "packageId=" + packageId +
                ", packageName='" + packageName + '\'' +
                ", rating=" + rating +
                ", duration=" + duration +
                ", price=" + price +
                '}';
    }
}
