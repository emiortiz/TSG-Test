package com.tsg.test;

public class User {

    public String username;
    public String encryptedPass;
    public String token;

    public User(String _username, String _encryptedPass, String _token){
        username = _username;
        encryptedPass = _encryptedPass;
        token = _token;
    }

}
