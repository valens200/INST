package com.example.demo.controller;


import com.example.demo.models.Post;
import com.example.demo.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/post")
@Slf4j
public class PostController {
    @Autowired
    PostService postService;
    @PostMapping("/create")
    public Post getAllPosts(@RequestBody Post post, HttpServletResponse response, HttpServletRequest request){
        response.setStatus(200);
        return postService.createPost(post);
    }
    @GetMapping("/post/{id}")
    public  Post getPOstById(@PathVariable int id, HttpServletRequest request, HttpServletResponse response){
        response.setStatus(200);
        return postService.getPostById(id);
    }

    @GetMapping("/posts")
    public  List<Post> createPost(@RequestBody Post post, HttpServletResponse response, HttpServletRequest request){
        response.setStatus(200);
        return  postService.getAllPosts();
    }
}
