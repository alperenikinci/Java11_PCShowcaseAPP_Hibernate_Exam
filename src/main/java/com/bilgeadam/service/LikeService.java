package com.bilgeadam.service;

import com.bilgeadam.repository.LikeRepository;
import com.bilgeadam.repository.entity.Like;
import com.bilgeadam.repository.entity.User;

import java.util.List;
import java.util.Optional;

public class LikeService {

    private LikeRepository likeRepository;

    public LikeService() {
        this.likeRepository = new LikeRepository();
    }

    public Like save(Like like) {
        return likeRepository.save(like);
    }

    public void update(Like like) {
        likeRepository.update(like);
    }

    public List<Like> findAll() {
        return likeRepository.findAll();
    }

    public Optional<Like> findById(Long id) {
        return likeRepository.findById(id);
    }

    public List<Like> saveAll(List<Like> likeList) {
        return (List<Like>) likeRepository.saveAll(likeList);
    }

    public void delete(Like like) {
        likeRepository.delete(like);
    }

    public void deleteById(Long id) {
        likeRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return likeRepository.existsById(id);
    }

    public List<Like> findByColumnNameAndValue(String columnName, String columnValue){
        return likeRepository.findByColumnNameAndValue(columnName, columnValue);
    }

    public boolean userAlreadyLikedAPost(Long id, Long postId) {
        return likeRepository.userAlreadyLikedAPost(id,postId);
    }
}
