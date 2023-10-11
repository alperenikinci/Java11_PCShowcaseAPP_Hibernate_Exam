package com.bilgeadam.service;

import com.bilgeadam.repository.PhotoRepository;
import com.bilgeadam.repository.entity.Photo;

import java.util.List;
import java.util.Optional;

public class PhotoService {
    private PhotoRepository photoRepository;

    public PhotoService() {
        this.photoRepository = new PhotoRepository();
    }

    public Photo save(Photo photo) {
        return photoRepository.save(photo);
    }

    public void update(Photo photo) {
        photoRepository.update(photo);
    }

    public List<Photo> findAll() {
        return photoRepository.findAll();
    }

    public Optional<Photo> findById(Long id) {
        return photoRepository.findById(id);
    }

    public List<Photo> saveAll(List<Photo> photoList) {
        return (List<Photo>) photoRepository.saveAll(photoList);
    }

    public void delete(Photo photo) {
        photoRepository.delete(photo);
    }

    public void deleteById(Long id) {
        photoRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return photoRepository.existsById(id);
    }

    public List<Photo> findByColumnNameAndValue(String columnName, String columnValue){
        return photoRepository.findByColumnNameAndValue(columnName, columnValue);
    }

}
