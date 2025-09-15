package com.ecosystem.backend.controller;

import com.ecosystem.backend.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureMockRestServiceServer
@AutoConfigureDataMongo
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepo userRepo;

    @BeforeEach
    void cleanDb() {
        userRepo.deleteAll();
    }


    @Test
    void getUserById_should_return_default_user() throws Exception {
        mockMvc.perform(get("/api/user/getUser/0"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                        "id":"0",
                        "displayName":"Demo User",
                        "jerseyNumber":"0",
                        "avatar_url":"http://localhost:5173/src/assets/free.png",
                        "gitHubId" : ""
                    }
                    """));
    }

    @Test
    void getAllUsers_should_return_all_users() throws Exception {
        mockMvc.perform(get("/api/user/getAllUsers"))
                .andExpect(status().isOk());
    }

}