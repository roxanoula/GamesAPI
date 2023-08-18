package com.games.stats.controllers;

import com.games.stats.entities.Games;
import com.games.stats.entities.Scores;
import com.games.stats.services.ScoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/v1/scores")
public class ScoresController {
    final private ScoresService scoresService;

    @Autowired
    public ScoresController(ScoresService scoresService) {
        this.scoresService = scoresService;
    }

    @GetMapping
    public List<Scores> getScores(){
        return scoresService.getScores();
    }

    @PostMapping
    public void addNewScore(@RequestBody Scores score) throws SQLException {
        scoresService.addNewScore(score);
    }

}
