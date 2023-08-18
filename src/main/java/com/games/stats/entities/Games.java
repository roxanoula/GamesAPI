package com.games.stats.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="games")
public class Games {
    @Id
    @SequenceGenerator(
            name = "game_id_sequence",
            sequenceName = "game_id_sequence",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "game_id_sequence"
    )
    private Long id;
    private String name;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Scores> scoresList = new ArrayList<>();

    public Games(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Games() {

    }

    public Long getId() { return id;}

    public String getName() { return name;}
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Game {" +
                "id = " + id +
                ", game_name = " + name +
                "}";
    }
}
