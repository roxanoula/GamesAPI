package com.games.stats.services;

import com.games.stats.entities.Users;
import com.games.stats.repositories.UsersRepository;
import jakarta.transaction.Transactional;
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
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Optional<Users> getUserByUsername(String username) {
        return usersRepository.findUserByUsername(username);
    }

    public void addNewUser(Users userData) throws SQLException {
        System.out.println(userData);
        Optional<Users> user = usersRepository.findUserByUsername(userData.getUsername());
        if (user.isPresent()) {
            throw new SQLException("The user with username: " + userData.getUsername() + " already exists");
        }

        usersRepository.save(userData);
    }

    @Transactional
    public void updateUser(String username, String firstName, String lastName) {
        Users user = usersRepository.findById(username)
                .orElseThrow(() -> new IllegalStateException("User with username: " + username + "does not exist in the database"));

        if (firstName != null && firstName.length() > 0) {
            user.setFirstName(firstName);
        }

        if (lastName != null && lastName.length() > 0) {
            user.setLastName(lastName);
        }
    }
}
