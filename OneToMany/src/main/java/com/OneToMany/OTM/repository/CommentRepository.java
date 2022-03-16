package com.OneToMany.OTM.repository;


import com.OneToMany.OTM.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

}