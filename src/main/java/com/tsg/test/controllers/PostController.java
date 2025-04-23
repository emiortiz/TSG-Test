package com.tsg.test.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tsg.test.entity.Post;
import com.tsg.test.service.PostsRepository;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostsRepository postsRepository;

    @GetMapping("/getPosts")
    public List<Post> getAllPost(@RequestParam long userId) {
        return postsRepository.findByIdUser(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody Post post){
      return postsRepository.save(post);
    }

    @GetMapping("/{id}")
    public List<Post> getPost(@RequestParam long id){
        return postsRepository.findByPostId(id);
    }

    @PostMapping("/update")
    public Post updatePost(@RequestBody Post post){
      return postsRepository.save(post);
    }
  
    @DeleteMapping()
    public String deletePost(@RequestBody Post post){
        postsRepository.delete(post);
        return post.getId() + "was deleted successfully";
    }

}
