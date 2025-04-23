package com.tsg.test.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsg.test.entity.Post;
import com.tsg.test.service.PostsRepository;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostsRepository postsRepository;

    @PostMapping
    public Post createStudent(@RequestBody Post student) {
        return postsRepository.save(student);
    }

    @GetMapping
    public List<Post> getAllStudents(@RequestBody long userId) {
        return postsRepository.findByPostId(userId);
    }
}
