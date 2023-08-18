package com.games.stats.repositories;

import com.games.stats.entities.Games;
import com.games.stats.entities.Scores;
import com.games.stats.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScoresRepository extends JpaRepository<Scores, Integer> {

}
