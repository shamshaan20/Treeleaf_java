package com.OneToMany.OTM;

import com.OneToMany.OTM.entity.Comment;
import com.OneToMany.OTM.entity.Post;
import com.OneToMany.OTM.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OtmApplication {

	public static void main(String[] args) {

		SpringApplication.run(OtmApplication.class, args);
	}
	@Autowired
	private PostRepository postRepository;


	public void run(String...args) throws Exception {

		Post post = new Post("one to many mapping using JPA and hibernate", "one to many mapping using JPA and hibernate");

		Comment comment1 = new Comment("Good");
		Comment comment2 = new Comment("Informative");
		Comment comment3 = new Comment("Nice Post");

		post.getComments().add(comment1);
		post.getComments().add(comment2);
		post.getComments().add(comment3);

		this.postRepository.save(post);
	}
}


