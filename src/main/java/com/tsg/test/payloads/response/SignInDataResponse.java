package com.tsg.test.payloads.response;

public class SignInDataResponse {
    private long id;
    private String username;

    public SignInDataResponse(long _id, String _username){
        this.id= _id;
        this.username= _username;
    }

    public long getId(){
        return id;
    }

    public String getUsername(){
        return username;
    }

    public void setId(long _id){
        this.id = _id;
    }

    public void setUsername(String _username){
        this.username = _username;
    }
    
}
