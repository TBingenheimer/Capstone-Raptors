package com.ecosystem.backend.service;

import com.ecosystem.backend.models.User;
import com.ecosystem.backend.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepo userRepo;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepo = Mockito.mock(UserRepo.class);
        userService = new UserService(userRepo);
    }

    @Test
    void testFindAllReturnsAllUsers() {
        List<User> mockUsers = Arrays.asList(
                new User("0", "Demo User", "0", "avatar1.png", "12345"),
                new User("1", "Bobo", "3280", "avatar2.png", "12346")
        );
        when(userRepo.findAll()).thenReturn(mockUsers);

        List<User> result = userService.findAll();

        assertEquals(2, result.size());
        assertEquals("Demo User", result.get(0).displayName());
        verify(userRepo, times(1)).findAll();
    }

    @Test
    void testGetUserById_UserExists() {
        User mockUser = new User("0", "Demo User", "0", "avatar1.png", "12345");
        when(userRepo.findById("0")).thenReturn(Optional.of(mockUser));

        User result = userService.getUserById("0");

        assertEquals("Demo User", result.displayName());
        verify(userRepo, times(1)).findById("0");
    }

    @Test
    void testGetUserById_UserNotFoundReturnsDemoUser() {
        when(userRepo.findById("0")).thenReturn(Optional.empty());

        User result = userService.getUserById("0");

        assertEquals("Demo User", result.displayName());
        assertEquals("0", result.id());
        verify(userRepo, times(1)).findById("0");
    }

    @Test
    void testGetUserByGithubId_UserExists() {
        User mockUser = new User("1", "Demo User", "0", "avatar1.png", "12345");
        when(userRepo.findByGitHubId("12345")).thenReturn(Optional.of(mockUser));

        User result = userService.getUserByGithubId("12345");

        assertEquals("Demo User", result.displayName());
        verify(userRepo, times(1)).findByGitHubId("12345");
    }

    @Test
    void testGetUserByGithubId_UserNotFoundReturnsDemoUser() {
        when(userRepo.findByGitHubId("12345")).thenReturn(Optional.empty());

        User result = userService.getUserByGithubId("12345");

        assertEquals("Demo User", result.displayName());
        verify(userRepo, times(1)).findByGitHubId("12345");
    }
}