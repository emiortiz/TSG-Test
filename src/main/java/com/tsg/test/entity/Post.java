package com.tsg.test.entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_user", nullable=false)
    private User user;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="creation_time")
    private LocalDate creation_time;

    public Post() { }

    public Post(User _user, String _title, String _description, LocalDate _creation_time) {
        this.user = _user;
        this.title = _title;
        this.description = _description;
        this.creation_time = _creation_time;
    }

    public long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    
    public void setUser(User _user) {
        this.user = _user;
    }

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String _title) {
        this.title= _title;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String _description) {
        this.description= _description;
    }
    
    public LocalDate getCreation_time() {
        return creation_time;
    }
    
    public void setDescription(LocalDate _creation_time) {
        this.creation_time= _creation_time;
    }

}
