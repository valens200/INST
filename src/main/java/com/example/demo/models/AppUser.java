package com.example.demo.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AppUser {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "userId")
    private int userId;
   @Column(name = "userName")
    private String userName;
   @Column(name = "email")
    private String email;
   @Column(name = "password")
    private String password;
   @ManyToMany(fetch = FetchType.EAGER)
   Collection<Role> roles = new ArrayList<>();
}
