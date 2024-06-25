package com.csc3402.lab.staff.controller;

import com.csc3402.lab.staff.model.User;
import com.csc3402.lab.staff.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
public class  LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid Credentials provided.");
        }
        return "redirect:/LogIn.html";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        User user = userService.findUserByEmail(username);
        if ("admin".equals(username) && "admin".equals(password)) {
            return "User/index";
        }
        else if (user != null && user.getPassword().equals(password)) {
            return "redirect:/index.html";
        }
        else {
            model.addAttribute("error", "Invalid Credentials provided.");
            return "redirect:/LogIn.html";
        }
    }

    @GetMapping("/adminPage")
    public String adminPage() {
        return "index";
    }

    @GetMapping("/userPage")
    public String userPage() {
        return "redirect:/index.html";
    }
}
