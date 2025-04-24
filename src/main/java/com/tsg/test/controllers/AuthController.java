package com.tsg.test.controllers;


import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tsg.test.security.JWTAuthtenticationConfig;
import com.tsg.test.security.services.UserDetailsImpl;
import com.tsg.test.service.UsersService;

import jakarta.validation.Valid;

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
  private UsersService usersService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostMapping("/signin")
  public ResponseEntity<SignInDataResponse> signIn(@Valid @RequestBody SignInDataRequest data){

    // Creamos un AutehnticationManager para validar las credenciales del usuario. 
    Authentication authentication = authenticationManager
      .authenticate(new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword()));

    // Almacenamos al usuario autenticado
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // Obtenemos el Usuario autenticado para generar el token
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    String token = jwtAuthtenticationConfig.getJWTToken(userDetails.getUsername());

    return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token.toString())
              .body(new SignInDataResponse(userDetails.getId(),
                                   userDetails.getUsername()));
  }

  @PostMapping("/signup")
  public ResponseEntity<SignUpDataResponse> signUp(@Valid @RequestBody SignUpDataRequest data) throws Exception{
    
    // valido si el username ya esta en uso
    if (!usersService.findOne(data.getUsername()).isEmpty()) {
      return ResponseEntity.badRequest().body(new SignUpDataResponse("Error: Username is already taken"));
    }

    User user = new User(data.getUsername(),passwordEncoder.encode(data.getPassword()), data.getEmail(), LocalDateTime.now(ZoneId.systemDefault()));

    // Guardo el nuevo usuario en la DB
    usersService.save(user);

    // Construye la URI para acceder a la información del nuevo usuario
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/api/users/{id}")
                .buildAndExpand(user.getId())
                .toUri();

    return ResponseEntity.created(location).body(new SignUpDataResponse("User created sucessfully"));
  }

}
