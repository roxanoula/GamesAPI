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
    public List<Scores> getScoresByGame(@RequestParam(name="gid") long gameId){
        return scoresService.getAllScoresByGame(gameId);
    }

    @PostMapping
    public void addNewScore(@RequestBody Scores score) throws SQLException {
        scoresService.addNewScore(score);
    }

}
