package com.ecosystem.backend.service;

import com.ecosystem.backend.repository.UserRepo;
import com.ecosystem.backend.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public List<User> findAll(){
        return userRepo.findAll();
    }
    public User getUserById(String id){

        User user = userRepo.findById(id)
                .orElseGet(()->new User(
                        "0",
                        "Demo User",
                        "0",
                        "http://localhost:5173/src/assets/free.png",
                        ""
                    )
                );


        return user;
    }
    public User getUserByGithubId(String gitHubId){
        Optional<User> user = userRepo.findByGitHubId(gitHubId);
        if(user.isEmpty()){
            return new User(
                    "0",
                    "Demo User",
                    "0",
                    "http://localhost:5173/src/assets/free.png",
                    "12345"
            );
        }else {
            return user.get();
        }
    }
}
