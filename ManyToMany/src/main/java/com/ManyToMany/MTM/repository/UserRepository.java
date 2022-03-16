package com.ManyToMany.MTM.repository;



import com.ManyToMany.MTM.entiity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}