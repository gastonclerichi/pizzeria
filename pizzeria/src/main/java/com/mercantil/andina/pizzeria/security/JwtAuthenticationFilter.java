package com.mercantil.andina.pizzeria.security;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter 
{
    private final AuthenticationManager authenticationManager;

public JwtAuthenticationFilter(AuthenticationManager authenticationManager) 
{
    this.authenticationManager = authenticationManager;
    setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL);
}

@Override
public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) 
{
    String  username = request.getParameter(SecurityConstants.PARAM_USUARIO);
    String  password = request.getParameter(SecurityConstants.PARAM_PASSWORD);
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

    return authenticationManager.authenticate(authenticationToken);
}

@Override
protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,FilterChain filterChain, Authentication authentication) 
{
    User user = ((User) authentication.getPrincipal());

    List<String> roles = user.getAuthorities()
        .stream()
        .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    

    byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();
    
    String token = Jwts.builder()
        .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
        .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
        .setIssuer(SecurityConstants.TOKEN_ISSUER)
        .setAudience(SecurityConstants.TOKEN_AUDIENCE)
        .setSubject(user.getUsername())
       // .setExpiration(new Date(Funciones.changeTimeZone(new Date(),TimeZone.getTimeZone("UTC")).getTime()+ Long.valueOf(SecurityConstants.TOKEN_TIME_EXPIRATION_MS)))
        .setExpiration(new Date(System.currentTimeMillis() + Long.valueOf(SecurityConstants.TOKEN_TIME_EXPIRATION_MS)))
        .claim("rol", roles)
        .compact();

    response.addHeader(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX + token);
    try 
    {
        response.getWriter().write(SecurityConstants.TOKEN_PREFIX + token);
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}
}