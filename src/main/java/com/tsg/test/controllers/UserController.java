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

import com.tsg.test.entity.Post;
import com.tsg.test.entity.User;
import com.tsg.test.service.PostsRepository;
import com.tsg.test.service.UsersRepository;

@RestController
@RequestMapping("/users/me")
public class UserController {

    @Autowired
    private PostsRepository postsRepository;
    
    @Autowired
    private UsersRepository usersRepository;
    

    @GetMapping("/posts/{userId}")
    public List<Post> getPosts(@RequestParam long userId){
        return postsRepository.findByIdUser(userId);
    }

    @PostMapping()
    public User updateUser(@RequestBody User user){
      return usersRepository.save(user);
    }
  
    @DeleteMapping()
    public String deleteUser(@RequestBody User user){
        //TODO: Tiene que devolver si lo pudo borrar o no aunquesea
        usersRepository.delete(user);
        return user.getId() + "was deleted successfully";
    }
}
