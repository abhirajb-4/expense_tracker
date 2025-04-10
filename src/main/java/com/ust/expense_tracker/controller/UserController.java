package com.ust.expense_tracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.ust.expense_tracker.model.User;
import com.ust.expense_tracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String home(Model model) {
        return "index"; // Loads home.html
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; // Loads register.html (Signup Form)
    }
    @PostMapping("/signup")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register"; // Reloads form with validation errors
        }
        if(userService.createUser(user)){
            model.addAttribute("successMessage", "User registered successfully!");
            return "index";
        }
        model.addAttribute("successMessage", "Email already exists");
        return "register";
         // Redirects to home.html after signup
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
