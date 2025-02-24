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
        if (userService.existsByEmail(user.getEmail())) { // Check for existing email
            model.addAttribute("error", "Email already exists!");
            return "register";
        }
        userService.registerUser(user);
        return "redirect:/login";
    }



    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
