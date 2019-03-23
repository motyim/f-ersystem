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
			System.out.println("# create dir");
			new File("uploads").mkdir();
			System.out.println("# Data Loaded into DB");
			User user = new User();
			user.setUsername("motyimm");
			user.setPhoneNumber("011147866144");
			userRepo.save(user);

			User user1= new User();
			user1.setUsername("saraa");
			user1.setPhoneNumber("010206060677");
			userRepo.save(user1);

            Employee employee = new Employee();
            employee.setName("admin");
            employee.setRole("admin");
            employee.setPhonenumber("0112455");
            employee.setPassword("123");
            employeeRepo.save(employee);

			Employee employee1 = new Employee();
			employee1.setName("emp");
			employee1.setRole("employee");
			employee1.setPhonenumber("0112455");
			employee1.setPassword("123");
			employeeRepo.save(employee1);


			Incident incident = new Incident();
			incident.setDate(new Date());
			incident.setEmployeeId(employee);
			incident.setUserId(user1);
			incident.setDescrption("بلاغ جديد");
			incident.setLocation("القاهره");
			incident.setLat("52.5");
			incident.setLng("13.4");
			incidentRepo.save(incident);

		};
	}

}

