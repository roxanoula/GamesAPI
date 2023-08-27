package com.games.stats.controllers;

import com.games.stats.entities.Scores;
import com.games.stats.services.ScoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(path="/api/v1/scores")
public class ScoresController {
    final private ScoresService scoresService;

    @Autowired
    public ScoresController(ScoresService scoresService) {
        this.scoresService = scoresService;
    }

    @GetMapping
    @RequestMapping(params="gid")
    public List<Scores> getAllScoresByGame(@RequestParam(name="gid") long gameId){
        return scoresService.getAllScoresByGame(gameId);
    }

    @GetMapping
    @RequestMapping(params={"user", "game"})
    public List<Scores> getScoresByUserAndGame(@RequestParam(name="user") String username,
                                               @RequestParam(name="game") String game) throws SQLException {
        return scoresService.getScoresByUserAndGame(username, game);
    }

    @PostMapping
    public void addNewScore(@RequestBody Scores score) throws SQLException {
        scoresService.addNewScore(score);
    }
}
