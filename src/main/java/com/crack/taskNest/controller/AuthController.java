package com.crack.taskNest.controller;

import com.crack.taskNest.entity.User;
import com.crack.taskNest.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute @Valid User user, Model model) {
        // Check for existing email and username
        if (userService.existsByEmail(user.getEmail())) {
            model.addAttribute("error", "Email already exists!");
            return "register";
        }

        if (userService.existsByUsername(user.getUsername())) {
            model.addAttribute("error", "Username already exists!");
            return "register";
        }

        userService.registerUser(user); // Register the user
        return "redirect:/login"; // Redirect to login page after successful registration
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Return the login view
    }
}
