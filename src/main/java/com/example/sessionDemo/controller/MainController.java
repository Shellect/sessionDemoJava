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

import java.security.Principal;

@Controller
@RequestMapping("/")
public class MainController {
    private UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String home(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        model.addAttribute("isAuthenticated", email != null);
        model.addAttribute("email", email);
        return "index";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute("loginData") LoginForm loginForm,
                            BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            return "login";
        }

        User user = new User(loginForm.getEmail(), loginForm.getPassword());
        session.setAttribute("email", user.getEmail());
        return "redirect:/";
    }

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
        if(userService.existsByEmail(registrationForm.getEmail())) {
            bindingResult.rejectValue("email",
                    "error.user",
                    "Пользователь уже зарегистрирован"
            );
            return "register";
        }

        // Сохранение пользователя
        User user = new User(registrationForm.getEmail(), registrationForm.getPassword());
        userService.saveUser(user);
        session.setAttribute("email", user.getEmail());

        return "redirect:/";
    }



}
