package com.tsg.test.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    @Column(name="id")
    private Long id;

    @JsonProperty("username")
    @Column(name="username", nullable=false, length=255)
    private String username;

    @JsonProperty("password")
    @Column(name="password", nullable=false, length=255)
    private String password;

    @JsonProperty("disable")
    @Column(name="disable", nullable=false)
    private int disable;

    public User(String _username, String _password){
        this.username= _username;
        this.password= _password;
        this.disable= 0;
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

    public int getDisable(){
        return disable;
    }
    
    public void setDisable(int _disable){
        this.disable = _disable;
    }
    
}
