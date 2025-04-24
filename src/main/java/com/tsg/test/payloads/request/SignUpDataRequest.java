package com.tsg.test.payloads.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * Clase encargada de mapear los datos provenientes del cliente 
 * al momento del sign up
 * 
 * Es igual a la clase signIn porque seria una mala practica acoplarlas. 
 * 
 */

public class SignUpDataRequest {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

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
