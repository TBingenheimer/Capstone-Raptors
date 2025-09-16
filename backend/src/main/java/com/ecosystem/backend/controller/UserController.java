package com.ecosystem.backend.controller;

import com.ecosystem.backend.service.UserService;
import com.ecosystem.backend.models.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getAllUsers")
    public List<User> findAll() {
        return userService.findAll();
    }
    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }
    @GetMapping("/getuserByGithubId/{gitHubId}")
    public User getUserByGithubId(@PathVariable String gitHubId) {
        return userService.getUserByGithubId(gitHubId);
    }

}
