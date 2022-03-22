package com.diwash.ManyToMany;

import com.diwash.ManyToMany.Models.Course;
import com.diwash.ManyToMany.Models.Tag;
import com.diwash.ManyToMany.repository.CourseRepository;
import com.diwash.ManyToMany.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;


@SpringBootApplication
public class ManyToManyApp implements CommandLineRunner {
	@Autowired
	CourseRepository courseRepositpory;
	@Autowired
	TagRepository tagRepository;
	public static void main(String[] args) {
		SpringApplication.run(ManyToManyApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Course data=new Course("PHP","Programming language",false);

		// Inserting the record in the Courses table.

		courseRepositpory.save(data);

		Tag t1=new Tag();
		t1.setId(1);
		t1.setName("java");
		t1.setId(2);
		t1.setName("php");
		tagRepository.save(t1);

	}
}