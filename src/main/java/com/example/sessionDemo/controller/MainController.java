package com.example.sessionDemo.controller;

import com.example.sessionDemo.entity.User;
import com.example.sessionDemo.model.LoginForm;
import com.example.sessionDemo.model.RegistrationForm;
import com.example.sessionDemo.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class MainController {
    private UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

//    @PostMapping("/login")
//    public String loginUser(@Valid @ModelAttribute LoginForm loginForm) {
////    public String loginUser(@RequestAttribute String email, @RequestAttribute String password) {
////        User user = new User(loginForm.getEmail(), loginForm.getPassword());
////        if (user.valid()) {
////            userService.saveUser(user);
////        } else {
////
////        }
//    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(
            @Valid @ModelAttribute("registrationForm") RegistrationForm registrationForm,
            BindingResult bindingResult,
            HttpSession session
    ) {
        if (bindingResult.hasErrors()) {
            return "register"; // Остаемся на странице, если есть ошибки
        }
        // Логика регистрации:
        // Проверка существования пользователя

        return "redirect:/";
    }

}
