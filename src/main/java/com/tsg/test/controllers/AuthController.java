package com.tsg.test.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.tsg.test.security.JWTAuthtenticationConfig;
import com.tsg.test.security.services.UserDetailsImpl;
import com.tsg.test.service.PostsRepository;
import com.tsg.test.service.UsersRepository;
import com.tsg.test.entity.User;
import com.tsg.test.payloads.request.SignInDataRequest;
import com.tsg.test.payloads.request.SignUpDataRequest;
import com.tsg.test.payloads.response.SignInDataResponse;
import com.tsg.test.payloads.response.SignUpDataResponse;



@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  AuthenticationManager authenticationManager;


  @Autowired
  JWTAuthtenticationConfig jwtAuthtenticationConfig;
  
  @Autowired
  private UsersRepository usersRepository;

  PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

  @PostMapping("/signin")
  public ResponseEntity<SignInDataResponse> signIn(@RequestBody SignInDataRequest data){

    Authentication authentication = authenticationManager
      .authenticate(new UsernamePasswordAuthenticationToken(data.getUser(), data.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    //Genero token para el usuario
    String token = jwtAuthtenticationConfig.getJWTToken(userDetails.getUsername());

    //Devuelvo al cliente mensaje de exito con su nuevo token
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, token.toString())
              .body(new SignInDataResponse(userDetails.getId(),
                                   userDetails.getUsername()));
  }

  @PostMapping("/signup")
  public ResponseEntity<SignUpDataResponse> signUp(@RequestBody SignUpDataRequest data){

    //valido si el username ya esta en uso
    if (usersRepository.findByUsername(data.getUsername()).isEmpty()) {
      return ResponseEntity.badRequest().body(new SignUpDataResponse("Error: Username is already taken"));
    }

    User user = new User(data.getUsername(), encoder.encode(data.getPassword()));

    //Guardo el nuevo usuario en la DB
    usersRepository.save(user);

    // Envio succes al cliente
    return ResponseEntity.ok().body(new SignUpDataResponse("User created sucessfully"));
  }

}
