package com.tsg.test.payloads.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * Clase encargada de mapear los datos provenientes del cliente 
 * al momento del sign in
 * 
 * Es igual a la clase signUp porque seria una mala practica acoplarlas. 
 * 
 */

public class SignInDataRequest {
    
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

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
}
