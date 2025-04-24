package com.tsg.test.entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name="Posts")
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty("id")
    @Column(name="id")
    private Long id;

    @JsonProperty("id_user")
    @ManyToOne
    @JoinColumn(name="id_user")
    private User user;

    @JsonProperty("title")
    @Column(name="title")
    private String title;

    @JsonProperty("description")
    @Column(name="description")
    private String description;

    @JsonProperty("creation_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="creation_time")
    private LocalDateTime  creation_time;

    @JsonProperty("modification_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="modification_time")
    private LocalDateTime  modification_time;

    public Post() { }

    public Post(User _user, long _id ,String _title, String _description, LocalDateTime  _creation_time,LocalDateTime  _modification_time) {
        this.id = _id;
        this.user = _user;
        this.title = _title;
        this.description = _description;
        this.creation_time = _creation_time;
        this.modification_time = _modification_time;
    }

    public Post(User _user ,String _title, String _description, LocalDateTime  _creation_time,LocalDateTime  _modification_time) {
        this.user = _user;
        this.title = _title;
        this.description = _description;
        this.creation_time = _creation_time;
        this.modification_time = _modification_time;
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
    
    public LocalDateTime  getCreation_time() {
        return creation_time;
    }
    
    public void setCreation_time(LocalDateTime  _creation_time) {
        this.creation_time= _creation_time;
    }

    public LocalDateTime  getModification_time() {
        return modification_time;
    }
    
    public void setModification_time(LocalDateTime  _modification_time) {
        this.modification_time= _modification_time;
    }

}
