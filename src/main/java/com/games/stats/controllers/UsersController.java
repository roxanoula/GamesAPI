package com.games.stats.controllers;

import com.games.stats.entities.Users;
import com.games.stats.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(path="/api/v1/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public List<Users> getUsers() {
        return usersService.getUsers();
    }

    @PostMapping
    public void addNewUser(@RequestBody Users user) throws SQLException {
        usersService.addNewUser(user);
    }
}
