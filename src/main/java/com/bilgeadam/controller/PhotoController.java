package com.bilgeadam.controller;

import com.bilgeadam.repository.entity.BaseEntity;
import com.bilgeadam.repository.entity.Photo;
import com.bilgeadam.repository.entity.Post;
import com.bilgeadam.service.PhotoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhotoController {

    Scanner scanner;
    PhotoService photoService;

    public PhotoController(){
        this.scanner = new Scanner(System.in);
        this.photoService = new PhotoService();
    }


    public List<Photo> uploadPhotos(Post post) {
        Photo photo = null;
        List<Photo> photoList = new ArrayList<>();
        Boolean addMorePhotos = true;
        do {
            System.out.print("Gonderinize eklemek istediginiz fotografin url'ini giriniz : ");
            String url = scanner.nextLine();
            photo = Photo.builder()
                    .baseEntity(BaseEntity.builder().build())
                    .postId(post.getId())
                    .photoUrl(url)
                    .build();
            photo = photoService.save(photo);
            photoList.add(photo);
            System.out.print("Baska fotograf eklemek ister misiniz ? (E/H) : ");
            String answer = scanner.nextLine();
            if(answer.equalsIgnoreCase("H")){
                addMorePhotos = false;
            }
        }while (addMorePhotos);
        return photoList;
    }
}
