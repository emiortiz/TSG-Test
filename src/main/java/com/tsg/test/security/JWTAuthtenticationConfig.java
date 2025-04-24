package com.tsg.test.security;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/*
 * Clase encargada de generar Tokens para usuarios autenticados
 * 
 */

@Configuration
public class JWTAuthtenticationConfig {
    
    public String getJWTToken(String username) {
       
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("espinozajgeJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Constans.TOKEN_EXPIRATION_TIME))
                .signWith(SecurityUtils.getSigningKey(Constans.SUPER_SECRET_KEY),  SignatureAlgorithm.HS512).compact();

        return Constans.TOKEN_BEARER_PREFIX + token; 
    }
}
