package com.tsg.test.payloads.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public class PostDataRequest {

    @NotNull(message = "id es requerido.")
    @JsonProperty("id")
    private Long id;

    @NotNull(message = "id_user es requerido.")
    @JsonProperty("id_user")
    private long id_user;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @NotNull(message = "The creation time is required.")
    @JsonProperty("creation_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime  creation_time;

    public PostDataRequest() { }

    public PostDataRequest(long _id,long _id_user, String _title, String _description, LocalDateTime  _creation_time) {
        this.id= _id;
        this.id_user= _id_user;
        this.title= _title;
        this.description= _description;
        this.creation_time= _creation_time;
    }

    public PostDataRequest(long _id_user, String _title, String _description, LocalDateTime  _creation_time) {
        this.id_user= _id_user;
        this.title= _title;
        this.description= _description;
        this.creation_time= _creation_time;
    }

    public long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public long getUserId() {
        return id_user;
    }
    
    public void setUserId(long _id_user) {
        this.id_user = _id_user;
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

}
