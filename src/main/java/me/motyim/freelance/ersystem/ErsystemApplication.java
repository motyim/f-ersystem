package me.motyim.freelance.ersystem;

import me.motyim.freelance.ersystem.entity.User;
import me.motyim.freelance.ersystem.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ErsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErsystemApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public CommandLineRunner demo(UserRepo userRepo) {
		return (args) -> {
			System.out.println("# Data Loaded into DB");
			User user = new User();
			user.setUsername("motyim");
			user.setPhoneNumber("01114786614");
			userRepo.save(user);
		};
	}

}

