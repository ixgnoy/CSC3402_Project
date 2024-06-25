package com.csc3402.lab.staff.service;


import com.csc3402.lab.staff.model.Package;
import com.csc3402.lab.staff.repository.PackageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackageServiceImpl implements PackageService {
    private final PackageRepository packageRepository;

    public PackageServiceImpl(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    @Override
    public List<Package> listAllPackages() {
        return packageRepository.findAll();
    }

    @Override
    public Package addNewPackage(Package package1) {
        return packageRepository.save(package1);
    }

    public Optional<Package> findPackageById(Integer id) {
        return packageRepository.findPackageById(id);
    }

    @Override
    public Package updatePackage(Package package1) {
        return packageRepository.save(package1);
    }
    @Override
    public void deletePackage(Package package1) {
        packageRepository.delete(package1);
    }
}
