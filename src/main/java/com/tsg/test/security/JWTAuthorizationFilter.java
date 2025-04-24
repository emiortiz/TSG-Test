package com.tsg.test.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/*
 * Clase encargada de autorizar cada token en cada Request
 * 
 */
@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private Claims setSigningKey(HttpServletRequest request) {
         
        String jwtToken = request.
            getHeader(Constans.HEADER_AUTHORIZACION_KEY).
            replace(Constans.TOKEN_BEARER_PREFIX, "");

        return Jwts.parserBuilder()
            .setSigningKey(SecurityUtils.getSigningKey(Constans.SUPER_SECRET_KEY))
            .build()
            .parseClaimsJws(jwtToken)
            .getBody();
   }
   
   private void setAuthentication(Claims claims) {

        List<String> authorities = (List<String>) claims.get("authorities");

        UsernamePasswordAuthenticationToken auth = 
            new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
            authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
      
        SecurityContextHolder.getContext().setAuthentication(auth);

   }

   private boolean isJWTValid(HttpServletRequest request, HttpServletResponse res) {

      String authenticationHeader = request.getHeader(Constans.HEADER_AUTHORIZACION_KEY);
        if (authenticationHeader == null || !authenticationHeader.startsWith(Constans.TOKEN_BEARER_PREFIX))
            return false;
        return true;
   }

   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, java.io.IOException {

        try {
         if (isJWTValid(request, response)) {
            Claims claims = setSigningKey(request);
            if (claims.get("authorities") != null) {
               setAuthentication(claims);
            } else {
               SecurityContextHolder.clearContext();
            }
         } else {
            SecurityContextHolder.clearContext();
         }
         filterChain.doFilter(request, response);
      } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {

         response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
         response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
         return;

      }
   }
}
