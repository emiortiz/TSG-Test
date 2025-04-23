package com.tsg.test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsg.test.security.JWTAuthtenticationConfig;

import com.tsg.test.User;

@RestController
public class LoginController {

 @Autowired
 JWTAuthtenticationConfig jwtAuthtenticationConfig;

 @PostMapping("login")
 public User login(
   @RequestParam("user") String username,
   @RequestParam("encryptedPass") String encryptedPass) {

  /**
   * En el ejemplo no se realiza la correcta validación del usuario
   */

  String token = jwtAuthtenticationConfig.getJWTToken(username);

  return new User(username, encryptedPass,token);
  
 }
}
