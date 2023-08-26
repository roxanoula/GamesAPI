package com.games.stats.controllers;

import com.games.stats.configurations.TestContainer;
import com.games.stats.entities.Games;
import com.games.stats.repositories.GamesRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GamesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GamesRepository gamesRepository;

    private static PostgreSQLContainer testContainer = new TestContainer().getTestContainer();

    @BeforeAll
    static void beforeAll() {
        try {
            testContainer.start();
        }catch (Exception e) {}
    }
    @AfterAll
    static void afterAll() {
        testContainer.stop();
    }

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(GamesController.class).build();
    }

    @Test
    public void testGetAllGames() throws Exception {
        Games game1 = new Games("game1");
        Games game2 = new Games("game2");
        Games game3 = new Games("game3");

        gamesRepository.saveAll(Arrays.asList(game1, game2, game3));

        //Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/games"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("game1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("game2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name", Matchers.is("game3")));

    }
}
