package com.bilgeadam.controller;

import com.bilgeadam.repository.entity.*;
import com.bilgeadam.service.PostService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PostController {
    Scanner scanner;
    PostService postService ;
    ComputerController computerController;
    PhotoController photoController;
    UserController userController;

    public PostController(){
        this.scanner = new Scanner(System.in);
        this.postService = new PostService();
        this.computerController = new ComputerController();
        this.photoController = new PhotoController();
        this.userController = new UserController();
    }

    public Post newPost(User user) {
        Post post = null;
        List<Computer> computerList = computerController.findAllByUser(user);
        computerList.forEach(System.out::println);
        System.out.print("Yeni gonderinizde hangi bilgisayarinizi paylasmak istiyorsunuz ?? : ");
        Long computerId = Long.parseLong(scanner.nextLine());
        Optional<Computer> computer = computerList.stream().filter(c -> c.getId()==computerId).findFirst();
        if(computer.isPresent()){
            post = Post.builder()
                    .computerId(computer.get().getId())
                    .userId(user.getId())
                    .baseEntity(BaseEntity.builder().build())
                    .build();
            post = postService.save(post);
            photoController.uploadPhotos(post);
            return post;
        } else {
            System.out.println("Paylasmak istediginiz bilgisayar bulunamamistir.");
            return null;
        }
    }

    public List<Post> findAll() {
        return postService.findAll();
    }

    public Optional<Post> findById(Long postId) {
        return postService.findById(postId);
    }

    public void update(Post post) {
        postService.update(post);
    }

    public List<Post> myLikedPosts(Long userId) {
        return postService.myLikedPosts(userId);
    }

    public List<Post> findPostsByUserId() {
        userController.findAll().stream()
                .forEach(u -> System.out.println(
                        "Id : " + u.getId() +
                        " Adi : "+ u.getName()+
                        " Soyadi : " + u.getSurname()));

        System.out.print("Lutfen gonderilerini goruntulemek istediginiz user'in id'sini giriniz : ");
        Long userId = Long.parseLong(scanner.nextLine());
        return postService.findPostsByUserId(userId);
    }
}
