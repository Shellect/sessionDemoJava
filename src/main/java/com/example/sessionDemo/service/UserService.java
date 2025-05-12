package com.example.sessionDemo.service;


import com.example.sessionDemo.entity.User;
import com.example.sessionDemo.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser(HttpSession session) {
        return userRepository.findByEmail((String) session.getAttribute("email"));
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public boolean existsByEmail(String email) {
        return userRepository.userExists(email);
    }
}
