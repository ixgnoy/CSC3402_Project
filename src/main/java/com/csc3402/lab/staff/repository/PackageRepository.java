package com.csc3402.lab.staff.repository;

import com.csc3402.lab.staff.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PackageRepository extends JpaRepository<Package, Integer> {
    @Query("SELECT p FROM Package p WHERE p.packageName = :name")
    Package findByName(@Param("name") String name);

    @Query("SELECT p FROM Package p WHERE p.packageId = :id")
    Optional<Package> findPackageById(@Param("id") Integer id);


}
