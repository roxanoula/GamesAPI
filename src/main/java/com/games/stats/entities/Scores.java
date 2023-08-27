package com.games.stats.entities;

import jakarta.persistence.*;

@Entity
@Table(name="scores")
public class Scores {
    @Id
    @SequenceGenerator(
            name = "scores_id_sequence",
            sequenceName = "scores_id_sequence ",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "scores_id_sequence"
    )
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name="GAMEID_FK"))
    private Games game;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name="USERNAME_FK"))
    private Users user;
    private Long score = Long.valueOf(0);

    public Scores(Long score, String game, String username) {
        this.id = id;
        this.score = score;
    }

    public Scores(Long score, Games game, Users user) {
        this.id = id;
        this.score = score;
        this.game = game;
        this.user = user;
    }

    public Scores() {}


    public Long getId() { return id;}

    public Games getGame() { return game;}
    public void setGame(Games game) {
        this.game = game;
    }
    public String getUsername() { return this.user.getUsername();}

    public Long getScore() { return score;}
    public void setScore(Long score) {
        this.score = score;
    }

    public Users getUser() { return user;}
    public void setUser(Users user) {
        this.user = user;
    }



    @Override
    public String toString() {
        return "Score {" +
                "id = " + id +
                ", game_id = " + game +
                ", username = " + user +
                ", score = " + score +
                "}";
    }


}
