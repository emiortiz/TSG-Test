package com.tsg.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsg.test.entity.Post;

@Service
public class PostsService {

    @Autowired
    private PostsRepository postRepository;

    @Transactional
    public Post save( Post post) throws Exception {

        return postRepository.save(post);
    }

    @Transactional(readOnly=true)
    public List<Post> findAll(long userId) throws Exception{

        List<Post> posts = postRepository.findByIdUser(userId);
        if (posts == null) {
            throw new Exception(
                    String.format("No post exists with user id=%d", userId));
        }
        return posts;
    }

    @Transactional(readOnly=true)
    public List<Post> findOne(long postId) throws Exception{

        List<Post> posts = postRepository.findByPostId(postId);
        if (posts == null) {
            throw new Exception(
                    String.format("No post exists with user id=%d", postId));
        }
        return posts;
    }

    @Transactional
    public Post delete( long postId) throws Exception{

        List<Post> postList = postRepository.findByPostId(postId);
        if (postList == null) {
            throw new Exception (
                    String.format("No post exists with id=%s", postId));
        }
        Post post = postList.iterator().next();
        postRepository.delete(post);

        return post;
    }

    @Transactional
    public Post update( Post post) throws Exception {

        List<Post> exist = postRepository.findByPostId(post.getId());
        if (exist == null) {
            throw new Exception (
                    String.format("No post exists with id=%s", post.getId()));
        }
        return postRepository.save(post);
    }
}
