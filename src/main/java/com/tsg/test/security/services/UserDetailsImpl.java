package com.tsg.test.security.services;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tsg.test.entity.User;


public class UserDetailsImpl implements UserDetails{
    private Long id;

    private String username;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String username, String password,
                                Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
    
    // No estaba contemplado roles en la solucion, pero UserDetailsService lo necesitaba. 
    // Caso de necesitarlo hay que implementarlo en la base de datos y en la clase User
    List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ADMIN"));;

    return new UserDetailsImpl(
        user.getId(), 
        user.getUsername(), 
        user.getPassword(), 
        authorities);
  }

    public Long getId() {
        return id;
    }
    
    @Override
    public String getPassword() {
        return password;
    }
    
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return authorities;
    }

}
