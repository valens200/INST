package com.example.demo.utils;


import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Slf4j
public class TokenGenerator {
    private UserDetails user;


    Map<String, String> messages = new HashMap<>();

    AlgorithmGenerator algorithmGenerator;
    public  TokenGenerator(UserDetails user, AlgorithmGenerator algorithmGenerator){
        this.user = user;
        this.algorithmGenerator = algorithmGenerator;

    }

    public String getAccessToken(HttpServletResponse response , HttpServletRequest request) throws IOException {
        if(user == null){
            response.setStatus(400);
            messages.put("error_message","Invalid  email or password");
            new ObjectMapper().writeValue(response.getOutputStream(), messages);

        }
            String acess_Token = JWT.create()
                    .withSubject(user.getUsername())
                    .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                    .withIssuer(request.getRequestURI().toString())
                    .withIssuedAt(new Date(System.currentTimeMillis()))
                    .withExpiresAt(new Date(System.currentTimeMillis() + 2 * 60 * 1000))
                    .sign(algorithmGenerator.getAlgorithm());

        return acess_Token;

    }

    public  String getfreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String refresh_Token;
        if (user == null) {
            response.setStatus(400);
            messages.put("error_message", "Invalid  email or password");
            new ObjectMapper().writeValue(response.getOutputStream(), messages);
            return null;
        }
        refresh_Token = JWT.create()
                .withSubject(user.getUsername())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withIssuer(request.getRequestURI().toString())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
                .sign(algorithmGenerator.getAlgorithm());
        return refresh_Token;
    }
}
