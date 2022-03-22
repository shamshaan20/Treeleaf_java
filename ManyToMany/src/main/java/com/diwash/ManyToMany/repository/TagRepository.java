package com.diwash.ManyToMany.repository;

import com.diwash.ManyToMany.Models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findTagsByCoursesId(Long courseId);
}