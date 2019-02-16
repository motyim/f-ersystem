package me.motyim.freelance.ersystem;

import me.motyim.freelance.ersystem.entity.Employee;
import me.motyim.freelance.ersystem.entity.User;
import me.motyim.freelance.ersystem.repo.EmployeeRepo;
import me.motyim.freelance.ersystem.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;

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
	public CommandLineRunner demo(UserRepo userRepo, EmployeeRepo employeeRepo) {
		return (args) -> {
			System.out.println("# create dir");
			new File("uploads").mkdir();
			System.out.println("# Data Loaded into DB");
			User user = new User();
			user.setUsername("motyim");
			user.setPhoneNumber("01114786614");
			userRepo.save(user);

			User user1= new User();
			user1.setUsername("sara");
			user1.setPhoneNumber("01020606067");
			userRepo.save(user1);

            Employee employee = new Employee();
            employee.setName("admin");
            employee.setRole("admin");
            employee.setPhonenumber("0112455");
            employee.setPassword("123");
            employeeRepo.save(employee);

		};
	}

}
