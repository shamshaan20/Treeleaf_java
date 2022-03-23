package com.diwash.ManyToMany;

import com.diwash.ManyToMany.Models.Employee;
import com.diwash.ManyToMany.Models.Project;
import com.diwash.ManyToMany.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ManyToManyApp implements CommandLineRunner {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void run(String...args) throws Exception {

		Employee employee = new Employee();
		employee.setFirstName("Diwash");
		employee.setLastName("Adhiakari");

		Employee employee1 = new Employee();
		employee1.setFirstName("Prajjwal");
		employee1.setLastName("Ghimire");

		Project project = new Project();
		project.setTitle("Banking Application");

		Project project1 = new Project();
		project1.setTitle("Intern Management System");

		// employee can work on two projects,  Add project references in the employee
		employee.getProjects().add(project);
		employee1.getProjects().add(project);
		employee.getProjects().add(project1);
		employee1.getProjects().add(project1);

		// Add employee reference in the projects
		project.getEmployees().add(employee);
		project1.getEmployees().add(employee1);

		project.getEmployees().add(employee1);
		project1.getEmployees().add(employee);


		employeeRepository.save(employee);
	}

	public static void main(String[] args) {
		SpringApplication.run(ManyToManyApp.class, args);
	}
}