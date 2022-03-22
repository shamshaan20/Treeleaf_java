package com.diwash.springboot;

import com.diwash.springboot.model.Employee;
import com.diwash.springboot.model.EmployeeAllDetail;
import com.diwash.springboot.repository.EmployeeRepository;
import com.diwash.springboot.repository.EmployeeAllDetailRepository;
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
	@Autowired
	private EmployeeAllDetailRepository employeeAllDetailRepository;
	@Override
	public void run(String... args) throws Exception {
		EmployeeAllDetail employeeAllDetail = new EmployeeAllDetail("treeleaf", 20);

		Employee employee = new Employee();
		employee.setFirstName("Diwash");
		employee.setLastName("Adhikari");
		employee.setEmail("adkdiwash1@gmail.com");
		employee.setEmployeeAllDetail(employeeAllDetail);

		employeeRepository.save(employee);
		employeeAllDetailRepository.save(employeeAllDetail);

		EmployeeAllDetail employeeAllDetail1 = new EmployeeAllDetail("abc", 10);

		Employee employee1 = new Employee();
		employee1.setFirstName("Bijay");
		employee1.setLastName("Maharjan");
		employee1.setEmail("bjmhrzn@gmail.com");
		employee1.setEmployeeAllDetail(employeeAllDetail1);

		employeeRepository.save(employee1);
		employeeAllDetailRepository.save(employeeAllDetail1);



	}
}
