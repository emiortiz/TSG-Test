package com.tsg.test.security.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tsg.test.entity.User;
import com.tsg.test.service.UsersService;

import jakarta.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsersService usersService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
            user = usersService.findOne(username).get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return UserDetailsImpl.build(user);
    }
}
