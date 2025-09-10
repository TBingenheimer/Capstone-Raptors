package com.ecosystem.backend.service;

import com.ecosystem.backend.repository.UserRepo;
import com.ecosystem.backend.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public List<User> findAll(){
        return userRepo.findAll();
    }
    public User getUserById(String id){

        User user = userRepo.findById(id)
                .orElseGet(()->new User("0", "Demo User", "0","http://localhost:5173/src/assets/free.png"));


        return user;
    }

}
