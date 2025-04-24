package com.tsg.test.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
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
import com.tsg.test.entity.User;
import com.tsg.test.payloads.request.PostDataRequest;
import com.tsg.test.service.PostsService;
import com.tsg.test.service.UsersService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostsService postsService;

    @Autowired
    private UsersService usersService;

    @GetMapping("/getPosts")
    public List<Post> getAllPost(@RequestParam long userId) throws Exception {
        return postsService.findAll(userId);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody PostDataRequest post) throws Exception{

      User user = usersService.findOne(post.getUserId()).get(0);
      Post newPost = new Post(user, post.getTitle(), post.getDescription(), post.getCreation_time(), LocalDateTime.now(ZoneId.systemDefault()));

      return postsService.save(newPost);
    }

    @GetMapping("/getPost")
    public List<Post> getPost(@RequestParam long postId) throws Exception{
        return postsService.findOne(postId);
    }

    @PostMapping("/update")
    public Post updatePost(@RequestBody PostDataRequest post) throws Exception{

      User user = usersService.findOne(post.getUserId()).get(0);
      Post postToUpdate = new Post(user, post.getId() ,post.getTitle(), post.getDescription(), post.getCreation_time(), LocalDateTime.now(ZoneId.systemDefault()));

      return postsService.save(postToUpdate);
    }
  
    @DeleteMapping("/delete")
    public String deletePost(@RequestParam long postId) throws Exception{
        postsService.delete(postId);
        return postId + " was deleted successfully";
    }

}
