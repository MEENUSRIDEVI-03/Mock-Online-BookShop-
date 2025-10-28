package com.onlinebookshop.controller;

import com.onlinebookshop.model.User;
import com.onlinebookshop.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository repo;
    public UserController(UserRepository repo) { this.repo = repo; }

    @PostMapping
    public User register(@RequestBody User user) {
        user.setJoinDate(LocalDateTime.now());
        return repo.save(user);
    }

    @GetMapping
    public List<User> getAll() {
        return repo.findAll();
    }
}
