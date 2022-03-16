package com.ManyToMany.MTM;

import com.ManyToMany.MTM.entiity.Role;
import com.ManyToMany.MTM.entiity.User;
import com.ManyToMany.MTM.repository.RoleRepository;
import com.ManyToMany.MTM.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.management.relation.RoleInfo;

@SpringBootTest
class MtmApplicationTests {

	@Autowired
	private UserRepository userRepository;
	private RoleRepository roleRepository;

	@Test
	void saveUser(){
		User user = new User();
		user.setFirstName("Prajjwal");
		user.setLastName("Ghimire");
		user.setEmail("Prajjwal@gmail.com");
		user.setPassword("secrete");

		Role admin = new Role();
		admin.setName("ROLE_ADMIN");

		Role customer = new Role();
		customer.setName("ROLE_CUSTOMER");

		user.getRoles().add(admin);
		user.getRoles().add(customer);

		userRepository.save(user);
	}

//	@Test
//	void updateUser(){
//		User user = userRepository.findById(1L).get();
//		user.setFirstName("Nikit");
//		user.setEmail("Nikit@gmail.com");
//
//		Role roleUser = new Role();
//		roleUser.setName("ROLE_USER");
//
//		user.getRoles().add(roleUser);
//
//		userRepository.save(user);
//	}

	@Test
	void fetchUser(){
		User user = userRepository.findById(1L).get();
		System.out.println(user.getEmail());
		user.getRoles().forEach((r) -> {
			System.out.println(r.getName());
		});
	}

//	@Test
//	void deleteUser(){
//		userRepository.deleteById(1L);
//	}

}
