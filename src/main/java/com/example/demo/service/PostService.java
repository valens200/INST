package com.example.demo.service;

import com.example.demo.models.Post;

import java.util.List;

public interface PostService {

    public Post createPost(Post post);
    public  Post getPostById(int id);
    public  Post getPostByPostName(String postName);
    public List<Post> getAllPosts();
    public String deletePost();
    public String deleteAllYourPost(int ownerId);

}
