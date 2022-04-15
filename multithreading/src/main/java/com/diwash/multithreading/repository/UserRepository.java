package com.diwash.multithreading.repository;

import com.diwash.multithreading.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
