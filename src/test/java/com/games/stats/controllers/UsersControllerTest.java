package com.games.stats.controllers;

import com.games.stats.entities.Users;
import com.games.stats.repositories.UsersRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsersControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsersRepository usersRepository;

    @Test
    @Transactional
    @Rollback
    public void testGetAllUsers() throws Exception {
        Users user1 = new Users("user1","a", "a");
        Users user2 = new Users("user2", "b", "b");
        Users user3 = new Users("user3");

        usersRepository.saveAll(Arrays.asList(user1, user2, user3));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username", Matchers.is("user1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", Matchers.is("a")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName", Matchers.is("a")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].username", Matchers.is("user2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName", Matchers.is("b")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName", Matchers.is("b")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].username", Matchers.is("user3")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].firstName", Matchers.nullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].lastName", Matchers.nullValue()));
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateFirstname() throws Exception {
        populateUsersTable();
        //Verify all users are inserted
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(4)));

        //Update only firstName
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/user1?firstName=User1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/user1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("User1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.nullValue()));

    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateLastName() throws Exception{
        populateUsersTable();
        //Verify all users are inserted
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(4)));
        //Update only lastName
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/user2?lastName=User2"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/user2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("first-name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is("User2")));
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateFirstAndLastName() throws Exception {
        populateUsersTable();
        //Verify all users are inserted
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(4)));
        //Update both first and last name
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("a")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is("b")));
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/user?lastName=last-name&firstName=first-name"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("first-name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is("last-name")));
    }

    public void populateUsersTable() {
        Users userNoName = new Users("user1");
        Users userWithFirstName = new Users("user2", "first-name", "");
        Users userWithLastName = new Users("user3", "", "last-name");
        Users user = new Users("user", "a", "b");

        List<Users> rs = usersRepository.saveAll(Arrays.asList(user, userNoName, userWithFirstName, userWithLastName));
    }
}
