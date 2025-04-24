package com.tsg.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsg.test.entity.Post;
import com.tsg.test.entity.User;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UsersService {
    
    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private PostsService postsService;

    @Transactional
    public User save( final User user) throws Exception {
        return userRepository.save(user);
    }

    @Transactional(readOnly=true)
    public List<User> findOne(String username) throws Exception{

        List<User> user = userRepository.findByUsername(username).map(Stream::of)
                       .orElseGet(Stream::empty)
                       .collect(Collectors.toList());

        if (user == null) {
            throw new Exception(
                    String.format("No user exists with id=%s", username));
        }
        return user;
    }

    @Transactional(readOnly=true)
    public List<User> findOne(long id) throws Exception{

        List<User> user = userRepository.findById(id).map(Stream::of)
                       .orElseGet(Stream::empty)
                       .collect(Collectors.toList());

        if (user == null) {
            throw new Exception(
                    String.format("No user exists with id=%d", id));
        }
        return user;
    }

    @Transactional(readOnly=true)
    public List<User> findAll() throws Exception{
        List<User> user = userRepository.findAll();
        return user;
    }

    @Transactional
    public User delete( String username) throws Exception{

        List<User> userList = userRepository.findByUsername(username).map(Stream::of)
                                .orElseGet(Stream::empty)
                                .collect(Collectors.toList());;

        if (userList == null) {
            throw new Exception (
                    String.format("No user exists with id=%s", username));
        }

        User userToDelete = userList.get(0);

        //Obtengo todos los post del usuario
        List<Post> postsToDelete = postsService.findAll(userToDelete.getId());

        //Los elimino para no romper la integridad de la BD
        for (Post post : postsToDelete) {
            postsService.delete(post.getId());
        }
 
        userRepository.delete(userToDelete);
        return userToDelete;
    }

    @Transactional
    public User update( User user) throws Exception {

        List<User> exist = userRepository.findById(user.getId()).map(Stream::of)
                                .orElseGet(Stream::empty)
                                .collect(Collectors.toList());;

        if (exist == null) {
            throw new Exception (
                    String.format("No user exists with id=%s", user.getId()));
        }
        return userRepository.save(user);
    }

}
