package com.diwash.ManyToMany.repository;



import com.diwash.ManyToMany.Models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findCoursesByTagsId(Long tagId);

    List<Course> findByPublished(boolean published);

    List<Course> findByTitleContaining(String title);
}