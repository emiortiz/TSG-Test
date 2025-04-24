package com.tsg.test.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Column(name="id")
    private Long id;

    @JsonProperty("username")
    @Column(name="username", nullable=false, length=255)
    private String username;

    @JsonProperty("password")
    @Column(name="password", nullable=false, length=255)
    private String password;

    @JsonProperty("email")
    @Column(name="email", nullable=false, length=255)
    private String email;

    @JsonProperty("creation_time")
    @Column(name="creation_time")
    private LocalDateTime  creation_time;

    public User(){ }

    public User(String _username, String _password, String _email ,LocalDateTime _creation_time){
        this.username= _username;
        this.password= _password;
        this.creation_time= _creation_time;
        this.email= _email;
    }

    public long getId(){
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String _email){
        this.email = _email;
    }

    public LocalDateTime  getCreation_time() {
        return creation_time;
    }
    
    public void setCreation_time(LocalDateTime  _creation_time) {
        this.creation_time= _creation_time;
    }
    
}
