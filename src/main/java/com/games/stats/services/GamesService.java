package com.games.stats.services;

import com.games.stats.entities.Games;
import com.games.stats.repositories.GamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GamesService {

    final private GamesRepository gamesRepository;

    @Autowired
    public GamesService(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }

    public List<Games> getGames() {
        return gamesRepository.findAll();
    }

    public void addNewGame(Games game) {
        gamesRepository.save(game);
    }
}
