package com.tsg.test.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsg.test.entity.User;

public interface UsersRepository extends JpaRepository<User, Long> {
    
    List<User> findByUsername(String username);

    List<User> findById(long id);

}
