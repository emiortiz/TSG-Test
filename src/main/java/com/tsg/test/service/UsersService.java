package com.tsg.test.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.tsg.test.entity.User;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public class UsersService {
    
    @Autowired
    private UsersRepository userRepository;

    @Transactional
    public User save( final User user) throws Exception {

        if (userRepository.existsById(user.getUserId())) {
            throw new Exception (
                    String.format("There already exists a user with id=%s", user.getUserId()));
        }
        return userRepository.save(user);
    }

    @Transactional(readOnly=true)
    public List<User> findOne(String username) throws Exception{

        List<User> user = userRepository.findByUsername(username);
        if (user == null) {
            throw new Exception(
                    String.format("No user exists with id=%d", username));
        }
        return user;
    }

    @Transactional
    public User delete( long userId) throws Exception{

        List<User> userList = userRepository.findById(userId);
        if (userList == null) {
            throw new Exception (
                    String.format("No user exists with id=%s", userId));
        }
        User user = userList.iterator().next();
        userRepository.delete(user);
        return user;
    }

    @Transactional
    public User update( User user) throws Exception {

        List<User> exist = userRepository.findById(user.getUserId());
        if (exist == null) {
            throw new Exception (
                    String.format("No user exists with id=%s", user.getUserId()));
        }
        return userRepository.save(user);
    }


}
