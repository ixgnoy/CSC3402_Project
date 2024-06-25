package com.csc3402.lab.staff.service;

import com.csc3402.lab.staff.model.Booking;
import com.csc3402.lab.staff.model.Package;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
@Service
public interface PackageService {

    List<Package> listAllPackages();

    Package addNewPackage(@Valid Package package1);
    Optional<Package> findPackageById(Integer id);

    Package updatePackage(Package package1);

    void deletePackage(Package package1);



}
