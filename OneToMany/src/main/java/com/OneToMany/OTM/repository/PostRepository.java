package com.OneToMany.OTM.repository;


import com.OneToMany.OTM.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}