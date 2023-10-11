package com.bilgeadam.controller;

import com.bilgeadam.repository.entity.BaseEntity;
import com.bilgeadam.repository.entity.Like;
import com.bilgeadam.repository.entity.Post;
import com.bilgeadam.repository.entity.User;
import com.bilgeadam.service.LikeService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class LikeController {

    LikeService likeService;
    PostController postController;
    private Scanner scanner;

    public LikeController(){
        this.likeService = new LikeService();
        this.scanner = new Scanner(System.in);
        this.postController = new PostController();

    }

    public Like likeAPost(User user) {
        Like like = null;
        Optional<Post> post =  null;
        List<Post> postList = postController.findAll();
        postList.forEach(System.out::println);
        System.out.print("Lutfen begenmek istediginiz gonderiyi seciniz : ");
        Long postId = Long.parseLong(scanner.nextLine());
        post = postController.findById(postId);

        if(post.isPresent()){
            boolean userAlreadyLiked = likeService.userAlreadyLikedAPost(user.getId(),postId);
            if(!userAlreadyLiked){
                like = Like.builder()
                        .userid(user.getId())
                        .postid(post.get().getId())
                        .baseEntity(BaseEntity.builder().build())
                        .build();
                likeService.save(like);
                post.get().setLikeCount(post.get().getLikeCount()+1);
                postController.update(post.get());
            } else{
                System.out.println("Bu gonderiyi zaten begendiniz...");
            }
        } else {
            System.out.println("Begenmeye calistiginiz post bulunamamaktadir... ");
        }
        return like;
    }
}
