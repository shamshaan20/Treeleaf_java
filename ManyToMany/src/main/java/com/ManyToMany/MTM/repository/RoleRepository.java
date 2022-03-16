package com.ManyToMany.MTM.repository;

import com.ManyToMany.MTM.entiity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}