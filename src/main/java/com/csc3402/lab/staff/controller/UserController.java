package com.csc3402.lab.staff.controller;

import com.csc3402.lab.staff.model.User;
import com.csc3402.lab.staff.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("list")
    public String showUserList(Model model) {
        model.addAttribute("users", userService.listAllUsers());
        return "User/list-user";
    }

    @GetMapping("signup")
    public String showAddNewUserForm(User user) {
        return "User/add-user";
    }

    @PostMapping("add")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "User/add-user";
        }
        userService.addNewUser(user);
        return "User/LogIn"; // Ensure this matches the path to your template
    }

    @GetMapping("update")
    public String showUpdateMainForm(Model model) {
        model.addAttribute("users", userService.listAllUsers());
        return "User/choose-user-to-update";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userService.findUserById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "User/update-user";
    }

    @PostMapping("update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setUserId((int) id);
            return "User/update-user";
        }
        userService.updateUser(user);
        model.addAttribute("users", userService.listAllUsers());
        return "redirect:/users/list";
    }

    @GetMapping("delete")
    public String showDeleteMainForm(Model model) {
        model.addAttribute("users", userService.listAllUsers());
        return "User/choose-user-to-delete";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userService.findUserById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userService.deleteUser(user);
        model.addAttribute("users", userService.listAllUsers());
        return "redirect:/users/list";
    }
}
