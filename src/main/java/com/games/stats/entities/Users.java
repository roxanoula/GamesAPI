package com.games.stats.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name="users")
public class Users {
    @Id
    private String username;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Scores> scoresList = new ArrayList<>();

    public Users(String username,String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }

    public Users(){

    }

    public String getUsername() { return username;}
    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() { return firstName;}
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() { return lastName;}
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User {" +
                "username = " + username +
                ", first_name = " + firstName +
                ", last_name = " + lastName +
                "}";
    }
}
