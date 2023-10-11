package com.bilgeadam.service;

import com.bilgeadam.repository.PostRepository;
import com.bilgeadam.repository.entity.Post;
import com.bilgeadam.repository.entity.User;

import java.util.List;
import java.util.Optional;

public class PostService {

    private PostRepository postRepository;

    public PostService() {
        this.postRepository = new PostRepository();
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public void update(Post post) {
        postRepository.update(post);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> saveAll(List<Post> postList) {
        return (List<Post>) postRepository.saveAll(postList);
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return postRepository.existsById(id);
    }
    public List<Post> findByColumnNameAndValue(String columnName, String columnValue){
        return postRepository.findByColumnNameAndValue(columnName, columnValue);
    }

    public List<Post> myLikedPosts(Long userId) {
        return postRepository.myLikedPosts(userId);
    }

    public List<Post> findPostsByUserId(Long userId) {
        return postRepository.findPostsByUserId(userId);
    }
}
