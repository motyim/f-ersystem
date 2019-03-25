package me.ersystem;

import me.ersystem.entity.Incident;
import me.ersystem.entity.User;
import me.ersystem.repo.EmployeeRepo;
import me.ersystem.repo.IncidentRepo;
import me.ersystem.repo.UserRepo;
import me.ersystem.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import java.io.File;
import java.util.Date;

@SpringBootApplication
@PropertySource("classpath:keys.properties")
public class ErsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErsystemApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public CommandLineRunner demo(UserRepo userRepo, EmployeeRepo employeeRepo, IncidentRepo incidentRepo) {
		return (args) -> {
			System.out.println("# create upload file ");
			new File("images").mkdir();
		};
	}

}

