package com.games.stats;

import com.games.stats.entities.Users;
import com.games.stats.repositories.UsersRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/")
public class StatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatsApplication.class, args);
	}

	@GetMapping
	public String welcomeMessage() {
		return "Welcome into the world of Games. Use the defined APIs to access the databases.";
	}

}
