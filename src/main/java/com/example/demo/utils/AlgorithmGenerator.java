package com.example.demo.utils;


import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AlgorithmGenerator {
    private Algorithm algorithm = Algorithm.HMAC256("instagram90847%%%".getBytes());


}
