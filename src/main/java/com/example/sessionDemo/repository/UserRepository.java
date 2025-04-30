package com.example.sessionDemo.repository;

import com.example.sessionDemo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class UserRepository {
    private final List<User> users = new ArrayList<>();

    public void save(User user) {
        users.add(user);
    }

    public User findByEmail(String email) {
        return users.stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst()
                .orElse(null);
    }

    public boolean userExists(String email) {
        return users.stream()
                .anyMatch(user -> Objects.equals(user.getEmail(), email));
    }
}
