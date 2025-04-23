package com.tsg.test.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="username", nullable=false, length=255)
    private String username;

    @Column(name="password", nullable=false, length=255)
    private String password;

    @Column(name="disable", nullable=false)
    private int disable;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="user")
    private List<Post> posts;

    public long getUserId(){
        return id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String _username){
        this.username = _username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String _password){
        this.password = _password;
    }

    public int getDisable(){
        return disable;
    }
    
    public void setDisable(int _disable){
        this.disable = _disable;
    }

    public List<Post> getPosts(){
        return posts;
    }
    
    public void setPosts(List<Post> _posts){
        this.posts = _posts;
    }
    
}
