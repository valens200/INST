package com.example.demo.service;

import com.example.demo.models.Post;
import com.example.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PostServiceImpl implements  PostService{

    @Autowired
    PostRepository postRepository;
    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post getPostById(int id) {
        return postRepository.getById(id);
    }

    @Override
    public Post getPostByPostName(String postName) {
        return null;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public String deletePost() {
        return null;
    }

    @Override
    public String deleteAllYourPost(int ownerId) {
         return null;
    }
}
