package com.example.demo.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.utils.AlgorithmGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    AlgorithmGenerator algorithmGenerator;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String authorization = request.getHeader("Authorizationn");
            if(authorization != null && authorization.startsWith("Bearer ")){
                String token = authorization.substring("Bearer ".length());
                JWTVerifier jwtVerifier = JWT.require(algorithmGenerator.getAlgorithm()).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(token);
                String roles[] = decodedJWT.getClaim("roles").asArray(String.class);
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                Arrays.stream(roles).forEach(role ->{
                    authorities.add(new SimpleGrantedAuthority(role));
                });
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(decodedJWT.getSubject(), null, authorities));
            }

        }catch(Exception exception){
            log.info("error  {}", exception.getMessage());

        }
        filterChain.doFilter(request, response);

    }
}
