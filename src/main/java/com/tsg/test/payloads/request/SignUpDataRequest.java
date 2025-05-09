package com.tsg.test.payloads.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern.Flag;
import jakarta.validation.constraints.Size;

/*
 * Clase encargada de mapear los datos provenientes del cliente 
 * al momento del sign up
 * 
 * Es igual a la clase signIn porque seria una mala practica acoplarlas. 
 * 
 */

public class SignUpDataRequest {

    @NotEmpty(message = "username es requerido.")
    @Size(min = 2, max = 255, message = "El largo del username debe ser entre 2 y 255 caracteres")
    @JsonProperty("username")
    private String username;

    @NotEmpty(message = "La contrasenia es requerida")
    @Size(min = 2, max = 255, message = "El largo del password debe ser entre 2 y 255 caracteres")
    @JsonProperty("password")
    private String password;

    @NotEmpty(message = "The email address is required.")
    @Email(message = "The email address is invalid.", flags = { Flag.CASE_INSENSITIVE })
    @JsonProperty("email")
    private String email;

    public String getUsername(){
        return username;
    }

    public void setUsername(String _username){
        this.username = _username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String _password){
        this.password = _password;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String _email){
        this.email = _email;
    }
}
