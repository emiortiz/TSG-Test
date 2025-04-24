package com.tsg.test.payloads.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern.Flag;
import jakarta.validation.constraints.Size;

public class UserDataRequest {

    @NotNull(message = "id es requerido.")
    @JsonProperty("id")
    private Long id;

    @NotEmpty(message = "username es requerido.")
    @Size(min = 2, max = 255, message = "El largo del username debe ser entre 2 y 255 caracteres")
    @JsonProperty("username")
    private String username;

    @NotEmpty(message = "password es requerido.")
    @Size(min = 2, max = 255, message = "El largo del username debe ser entre 2 y 255 caracteres")
    @JsonProperty("password")
    private String password;

    @NotEmpty(message = "The email address is required.")
    @Email(message = "The email address is invalid.", flags = { Flag.CASE_INSENSITIVE })
    @JsonProperty("email")
    private String email;

    @NotNull(message = "The creation time is required.")
    @JsonProperty("creation_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime  creation_time;

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
