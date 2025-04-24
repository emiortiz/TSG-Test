package com.tsg.test.payloads.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/*
 * Clase encargada de mapear los datos provenientes del cliente 
 * al momento del sign in
 * 
 * Es igual a la clase signUp porque seria una mala practica acoplarlas. 
 * 
 */

public class SignInDataRequest {
    
    @NotEmpty(message = "username es requerido.")
    @Size(min = 2, max = 255, message = "El largo del username debe ser entre 2 y 255 caracteres")
    @JsonProperty("username")
    private String username;

    @NotEmpty(message = "La contrasenia es requerida")
    @Size(min = 2, max = 255, message = "El largo del password debe ser entre 2 y 255 caracteres")
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
