package com.csc3402.lab.staff.controller;

import com.csc3402.lab.staff.model.Package;
import com.csc3402.lab.staff.service.BookingService;
import com.csc3402.lab.staff.service.PackageService;
import com.csc3402.lab.staff.service.PaymentService;
import com.csc3402.lab.staff.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/package")
public class PackageController {
    private final PackageService packageService;
    private final BookingService bookingService;
    private final PaymentService paymentService;
    private final UserService userService;

    public PackageController(PackageService packageService, BookingService bookingService, PaymentService paymentService, UserService userService) {
        this.packageService = packageService;
        this.bookingService = bookingService;
        this.paymentService = paymentService;
        this.userService = userService;
    }

    @GetMapping("list")
    public String showPackageList(Model model) {
        model.addAttribute("packages", packageService.listAllPackages());
        return "Package/list-package";
    }

    @GetMapping("assign")
    public String showAssignPackageForm(Model model) {
        model.addAttribute("package", new Package());
        model.addAttribute("users", userService.listAllUsers());
        return "Package/add-package";
    }

    @PostMapping("add")
    public String addPackage(@Valid Package aPackage, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "Package/add-package";
        }

        packageService.addNewPackage(aPackage);
        return "redirect:/package/list";
    }

    @GetMapping("update")
    public String showUpdateMainForm(Model model) {
        model.addAttribute("packages", packageService.listAllPackages());
        return "Package/choose-package-to-update";
    }

    @GetMapping("edit/{id}")
    public String showUpdatePackageForm(@PathVariable("id") Integer id, Model model) {
        Package aPackage = packageService.findPackageById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid package Id:" + id));
        model.addAttribute("package", aPackage);
        return "Package/update-package";
    }

    @PostMapping("update/{id}")
    public String updatePackage(@PathVariable("id") Integer id, @Valid Package aPackage, BindingResult result, Model model){
        if (result.hasErrors()) {
            aPackage.setPackageId(id);
            return "index";
        }
        packageService.updatePackage(aPackage);
        model.addAttribute("packages", packageService.listAllPackages());
        return "redirect:/package/list";
    }

    @GetMapping("delete")
    public String showDeletePackageForm(Model model){
        model.addAttribute("packages", packageService.listAllPackages());
        return "Package/delete-package";
    }

    @GetMapping("delete/{id}")
    public String deletePackage(@PathVariable("id") Integer id, Model model){
        Package aPackage = packageService.findPackageById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid package Id:" + id));
        packageService.deletePackage(aPackage);
        model.addAttribute("packages", packageService.listAllPackages());
        return "redirect:/package/list";
    }

}