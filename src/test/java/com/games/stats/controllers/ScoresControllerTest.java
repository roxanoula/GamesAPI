package com.games.stats.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.games.stats.configurations.TestContainer;
import com.games.stats.entities.Games;
import com.games.stats.entities.Scores;
import com.games.stats.entities.Users;
import com.games.stats.repositories.GamesRepository;
import com.games.stats.repositories.ScoresRepository;
import com.games.stats.repositories.UsersRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ScoresControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ScoresRepository scoresRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private GamesRepository gamesRepository;

    private static PostgreSQLContainer testContainer = new TestContainer().getTestContainer();

    @BeforeAll
    public static void beforeAll(){
        testContainer.start();
    }

    @AfterAll
    public static void afterAll() {
        testContainer.close();
    }

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(ScoresController.class).build();
    }

    @Test
    public void getAllScoresForUserAndGameTest() throws Exception {
        Users user1 = new Users("user1","a", "a");
        Users user2 = new Users("user2", "b", "b");
        Users user3 = new Users("user3");

        usersRepository.saveAll(Arrays.asList(user1, user2, user3));

        Games game1 = new Games("game1");
        Games game2 = new Games("game2");
        Games game3 = new Games("game3");

        gamesRepository.saveAll(Arrays.asList(game1, game2, game3));

        Scores s1 = new Scores(500L, game1, user1);
        Scores s2 = new Scores(1500L, game1, user1);
        Scores s3 = new Scores(100L, game1, user1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/scores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(s1)))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/scores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(s2)))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/scores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(s3)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/scores?user=user1&game=game1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].score", Matchers.is(500)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].score", Matchers.is(1500)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].score", Matchers.is(100)));


    }

}
