package com.games.stats.repositories;

import com.games.stats.entities.Games;
import com.games.stats.entities.Scores;
import com.games.stats.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springfox.documentation.annotations.Cacheable;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScoresRepository extends JpaRepository<Scores, Integer> {
    //@Cacheable("scores-for-game")
    @Query(value="SELECT s from Scores s WHERE s.user.username = ?1 and s.game.id = ?2")
    List<Scores> getScoresByUserAndGame(String username, long gameId);

    @Query(value="SELECT s from Scores s WHERE s.game.id = ?1")
    List<Scores> findAllScoresByGame(long gameId);

}
