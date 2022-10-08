package com.example.demo.utils;


import com.auth0.jwt.JWT;
import com.example.demo.models.AppUser;
import com.example.demo.models.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Data
@Slf4j
@NoArgsConstructor
public class SignUpTokenGenerator {
    private AppUser registeredUser;
    Collection<Role> roles;
    Collection<GrantedAuthority> authorities;
    AlgorithmGenerator algorithmGenerator;


    public  SignUpTokenGenerator(AppUser registeredUser, Collection<Role> roles, Collection<GrantedAuthority> grantedAuthorities, AlgorithmGenerator algorithmGenerator){
        this.registeredUser = registeredUser;
        this.authorities = grantedAuthorities;
        this.roles  = roles;
        this.algorithmGenerator = algorithmGenerator;
    }

    public String getAccessTOken(HttpServletResponse response, HttpServletRequest request){
        String acess_Token = JWT.create()
                .withSubject(registeredUser.getUserName())
                .withClaim("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withIssuer(request.getRequestURI().toString())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 2 * 60 * 1000))
                .sign(algorithmGenerator.getAlgorithm());
        return acess_Token;
    }

    public  String getRefreshToken(HttpServletRequest request, HttpServletResponse response){
        String refresh_Token = JWT.create()
                .withSubject(registeredUser.getUserName())
                .withClaim("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withIssuer(request.getRequestURI().toString())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
                .sign(algorithmGenerator.getAlgorithm());
        return refresh_Token;
    }
}
