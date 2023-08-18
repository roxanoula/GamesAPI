package com.games.stats.services;
import com.games.stats.entities.Games;
import com.games.stats.entities.Scores;
import com.games.stats.entities.Users;
import com.games.stats.repositories.GamesRepository;
import com.games.stats.repositories.ScoresRepository;
import com.games.stats.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class ScoresService {

    private final ScoresRepository scoresRepository;
    private final GamesRepository gamesRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public ScoresService(ScoresRepository scoresRepository, GamesRepository gamesRepository, UsersRepository usersRepository){
        this.scoresRepository = scoresRepository;
        this.gamesRepository = gamesRepository;
        this.usersRepository = usersRepository;
    }

    public List<Scores> getScores() {
        return scoresRepository.findAll();
    }

    public void addNewScore(Scores score) throws SQLException {

        Optional<Games> gameOptional = gamesRepository.findByName(score.getGame().getName());
        Optional<Users> userOptional = usersRepository.findUserByUsername(score.getUsername());

        if (!gameOptional.isPresent()) {
            Games newGame = gamesRepository.save(score.getGame());
            score.setGame(newGame);
        }
        else {
            score.setGame(gameOptional.get());
        }

        if (!userOptional.isPresent()) {
            throw new SQLException("There is no user in the system with username: " + score.getUsername());
        }
        else {
            score.setUser(userOptional.get());
        }


        scoresRepository.save(score);
    }
}
