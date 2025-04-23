package com.tsg.test.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tsg.test.entity.Post;

public interface PostsRepository extends JpaRepository<Post, Long>{
    
    @Query("SELECT i FROM Post i WHERE i.users.id_user = ?1")
    List<Post> findByUserId(long id_user);

    @Query("SELECT i FROM Post i WHERE i.id = ?1")
    List<Post> findByPostId(Long id);

}
