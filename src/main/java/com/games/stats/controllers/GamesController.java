package com.games.stats.controllers;

import com.games.stats.entities.Games;
import com.games.stats.services.GamesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/games")
public class GamesController {
    final private GamesService gamesService;

    public GamesController(GamesService gamesService){
        this.gamesService = gamesService;
    }
    @GetMapping
    public List<Games> getGames() {
        return gamesService.getGames();
    }

    @PostMapping
    public void addNewGame(@RequestBody Games game) {
        gamesService.addNewGame(game);
    }
}
