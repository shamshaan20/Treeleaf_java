package com.diwash.springboot;

import com.diwash.springboot.model.Employee;
import com.diwash.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootbackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootbackendApplication.class, args);
	}
	@Autowired
	private EmployeeRepository employeeRepository;
	@Override
	public void run(String... args) throws Exception {
		Employee employee = new Employee();
		employee.setFirstName("Diwash");
		employee.setLastName("Adhikari");
		employee.setEmailId("adkdiwash1@gmail.com");
		employeeRepository.save(employee);

		Employee employee1 = new Employee();
		employee1.setFirstName("Bijay");
		employee1.setLastName("Maharjan");
		employee1.setEmailId("bjmhrzn@gmail.com");
		employeeRepository.save(employee1);

	}
}
