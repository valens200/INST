package com.example.demo.repository;

import com.example.demo.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<AppUser , Integer> {

     public  AppUser findByEmail(String username);
}
