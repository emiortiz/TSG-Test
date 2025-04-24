package com.tsg.test.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsg.test.entity.User;
import com.tsg.test.payloads.request.UserDataRequest;
import com.tsg.test.service.UsersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UsersService usersService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/getOne")
    public List<User> getOneUser(@RequestParam String username) throws Throwable {
        return usersService.findOne(username);
    }

    @GetMapping("/getAll")
    public List<User> getAllUser() throws Throwable {
        return usersService.findAll();
    }
    
    @PostMapping("/update")
    public User updateUser(@Valid @RequestBody UserDataRequest user) throws Exception{
        return usersService.save(new User(user.getId(), user.getUsername(), passwordEncoder.encode(user.getPassword()), user.getEmail(), user.getCreation_time()));
    }
  
    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam String username) throws Exception{
        usersService.delete(username);
        return username + "was deleted successfully";
    }
}
