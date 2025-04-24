package com.tsg.test.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tsg.test.security.services.UserDetailsServiceImpl;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {
    
        @Autowired
        JWTAuthorizationFilter jwtAuthorizationFilter;

        @Autowired
        UserDetailsServiceImpl userDetailsService;


        @Bean
        public DaoAuthenticationProvider authenticationProvider() {
                DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
                authProvider.setUserDetailsService(userDetailsService);
                authProvider.setPasswordEncoder(passwordEncoder());
   
                return authProvider;
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
                return authConfig.getAuthenticationManager();
        }

        @Bean
        public static PasswordEncoder passwordEncoder() {
                return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain configure(HttpSecurity http) throws Exception {

                http    // Deshabilitamos Cross-Site Request Forgery 
                        .csrf((csrf) -> csrf
                        .disable())

                        // Autorizamos todos los post desde el login, sino autentificar
                        .authorizeHttpRequests( authz -> authz
                                .requestMatchers(HttpMethod.POST, Constans.AUTH_URL).permitAll()
                                .anyRequest().authenticated())
                
                        // Agregarmos un filtro de autentificacion de usuario y contraseña
                        .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }
}
