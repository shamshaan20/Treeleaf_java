package com.diwash.OneToMany.repository;

import com.diwash.OneToMany.Models.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Integer> {
}
