package com.games.stats.services;

import com.games.stats.entities.Users;
import com.games.stats.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    final private UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    public List<Users> getUsers() {
        return usersRepository.findAll();
    }

    public void addNewUser(Users userData) throws SQLException {
        System.out.println(userData);
        Optional<Users> user = usersRepository.findUserByUsername(userData.getUsername());
        if (user.isPresent()) {
            throw new SQLException("The user with username: " + userData.getUsername() + " already exists");
        }

        usersRepository.save(userData);
    }
}
