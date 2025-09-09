package com.ecosystem.backend.controller;

import com.ecosystem.backend.repository.UserRepo;
import com.ecosystem.backend.tournaments.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @GetMapping("/getAllUsers")
    public List<User> findAll() {
        return userRepo.findAll();
    }
    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable String id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));;
        System.out.println(user);
        //return "";
         return user;
    }

}
