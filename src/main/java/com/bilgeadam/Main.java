package com.bilgeadam;

import com.bilgeadam.controller.*;
import com.bilgeadam.repository.entity.Computer;
import com.bilgeadam.repository.entity.ComputerSpec;
import com.bilgeadam.repository.entity.Post;
import com.bilgeadam.repository.entity.User;

import java.util.List;
import java.util.Scanner;

public class Main {

    Scanner scanner;
    ComputerController computerController;
    ComputerSpecController computerSpecController;
    LikeController likeController;
    PhotoController photoController;
    PostController postController;
    UserController userController;

    public Main(){
        this.scanner = new Scanner(System.in);
        this.computerController = new ComputerController();
        this.computerSpecController = new ComputerSpecController();
        this.likeController = new LikeController();
        this.photoController = new PhotoController();
        this.postController = new PostController();
        this.userController = new UserController();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.pcShowcaseApp();
    }

    private void pcShowcaseApp() {
        String secim;
        do {
            System.out.println("1- Giris Yap ");
            System.out.println("2- Kayit Ol ");
            System.out.println("0- Cikis Yap ");
            System.out.print("Seciminiz : ");
            secim = scanner.nextLine();

            switch (secim){
                case "1":{
                    userMenu(userController.login());
                    break;
                }
                case "2":{
                    User user = userController.createAccount();
                    break;
                }
                case "0":{
                    break;
                }
            }
        }while (!secim.equals("0"));
    }

    private void userMenu(User user) {
        String secim;
        do {
            System.out.println("1- Bilgisayar Bileseni Olustur");
            System.out.println("2- Bilgisayar Olustur");
            System.out.println("3- Yeni Post");
            System.out.println("4- Post Begen");
            System.out.println("5- Postlari Goruntule");
            System.out.println("6- Begendigim Gonderiler");
            System.out.println("7- Bir Kullanicinin Gonderilerini Listele");
            System.out.println("0- Oturumu Sonlandir");
            System.out.print("Seciminiz : ");
            secim = scanner.nextLine();

            switch (secim){
                case "1":{
                    computerSpecController.createSpec(user);
                    break;
                }
                case "2":{
                    Computer computer = computerController.createComputer(user);
                    break;
                }
                case "3":{
                    Post post = postController.newPost(user);
                    break;
                }
                case "4":{
                    likeController.likeAPost(user);
                    break;
                }
                case "5":{
                    postController.findAll().forEach(System.out::println);
                    break;
                }
                case "6":{
                    postController.myLikedPosts(user.getId()).forEach(System.out::println);
                    break;
                }
                case "7":{
                    postController.findPostsByUserId().forEach(System.out::println);
                    break;
                }
                case "0":{
                    System.out.println("Cikis yapiliyor... ");
                    break;
                }
                default:{
                    System.out.println("Yanlis secim yaptiniz. Lutfen menudeki seceneklerden birini secin!!! ");
                    break;
                }
            }
        }while (!secim.equals("0"));

    }


}