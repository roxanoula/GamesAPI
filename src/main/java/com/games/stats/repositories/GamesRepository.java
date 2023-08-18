package com.games.stats.repositories;

import com.games.stats.entities.Games;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface GamesRepository extends JpaRepository<Games, Integer> {
    Optional<Games> findById(Long id);
    Optional<Games> findByName(String name);
}
