package com.tsg.test.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsg.test.entity.User;
import com.tsg.test.service.UsersService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UsersService usersService;

    @GetMapping("/getOne")
    public List<User> getOneUser(@RequestParam String username) throws Throwable {
        return usersService.findOne(username);
    }

    @GetMapping("/getAll")
    public List<User> getAllUser() throws Throwable {
        return usersService.findAll();
    }
    
    @PostMapping("/update")
    public User updateUser(@RequestBody User user) throws Exception{
        return usersService.save(user);
    }
  
    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam String username) throws Exception{
        usersService.delete(username);
        return username + "was deleted successfully";
    }
}
