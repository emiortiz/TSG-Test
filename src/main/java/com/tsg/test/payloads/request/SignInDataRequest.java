package com.tsg.test.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * Clase encargada de mapear los datos provenientes del cliente 
 * al momento del sign in
 * 
 * Es igual a la clase signUp porque seria una mala practica acoplarlas. 
 * 
 */

public class SignInData {
    
    @JsonProperty("user")
    private String user;

    @JsonProperty("password")
    private String password;

    public String getUser(){
        return user;
    }

    public void setUser(String _user){
        this.user = _user;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String _password){
        this.password = _password;
    }
}
