package com.diwash.OneToMany;

import com.diwash.OneToMany.Models.Manufactures;
import com.diwash.OneToMany.Models.Model;
import com.diwash.OneToMany.repository.ManufacturesRepository;
import com.diwash.OneToMany.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OneToManyApp implements CommandLineRunner {

	@Autowired
	ManufacturesRepository manufacturesRepo;

	@Autowired
	ModelRepository modelRepo;

	public static void main(String[] args) {
		SpringApplication.run(OneToManyApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Manufactures data = new Manufactures(1, "Tvs");


		// Inserting the record in the Manufactures tables
		manufacturesRepo.save(data);

		// Now try to mapped above record with multiple models
		Model model1 = new Model(1, "N-troq", data);
		Model model2 = new Model(2, "Apachee", data);
		modelRepo.save(model1);
		modelRepo.save(model2);

		Manufactures data1 = new Manufactures(2, "Honda");

		manufacturesRepo.save(data1);

		Model model3 = new Model(3, "Grazia", data1);
		Model model4 = new Model(4, "Dio", data1);

		modelRepo.save(model3);
		modelRepo.save(model4);

	}
}