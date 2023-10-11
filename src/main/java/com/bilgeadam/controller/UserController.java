package com.bilgeadam.controller;

import com.bilgeadam.repository.entity.BaseEntity;
import com.bilgeadam.repository.entity.User;
import com.bilgeadam.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserController {

    private UserService userService;
    private Scanner scanner;

    public UserController(){
        this.userService = new UserService();
        this.scanner = new Scanner(System.in);
    }


    public User login() {
        String email;
        String password;
        Boolean dataIntegrity = false; //veri  butunlugu
        Optional<User> user = null;
        List<String> emailList = userService.findAll().stream().map(u -> u.getEmail()).collect(Collectors.toList());
        do {
            System.out.print("Lutfen emailinizi giriniz : ");
            email = scanner.nextLine();
            if(emailList.contains(email)){
                user = userService.findByEmail(email);
                do {
                    System.out.print("Lutfen sifrenizi giriniz : ");
                    password = scanner.nextLine();
                    if (user.get().getPassword().equals(password)){
                        dataIntegrity = true;
                    } else {
                        System.out.println("Yanlis sifre girdiniz!!! ");
                        dataIntegrity = false;
                    }
                }while (!dataIntegrity);
            } else{
                System.out.println("Girdiginiz email ile eslesen bir kullanici bulunmamaktadir... ");
                dataIntegrity = false;
            }

        }while(!dataIntegrity);
        return user.get();
    }

    public User createAccount() {
        String email;
        String password;
        Boolean dataIntegrity = false;
        List<String> emailList = userService.findAll().stream().map(u -> u.getEmail()).collect(Collectors.toList());
        do {
            System.out.print("Email giriniz : ");
            email = scanner.nextLine();
            if(emailList.contains(email)){
                System.out.println("Girdiginiz email kullanilmaktadir...");
                dataIntegrity =false;
            } else{
                dataIntegrity = true;
            }
        }while(!dataIntegrity);

        do {
            System.out.print("Sifrenizi giriniz : ");
            password = scanner.nextLine();
            System.out.print("Sifrenizi tekrar giriniz : ");
            String rePassword = scanner.nextLine();
            if(!password.equals(rePassword)){
                System.out.println("Girdiginiz sifreler eslesmiyor!! ");
                dataIntegrity = false;
            } else{
                dataIntegrity = true;
            }
        }while (!dataIntegrity);

        System.out.print("Lutfen adinizi giriniz : ");
        String name = scanner.nextLine();
        System.out.print("Lutfen soyadinizi giriniz : ");
        String surname = scanner.nextLine();

        User user = User.builder()
                .baseEntity(BaseEntity.builder().build())
                .email(email)
                .password(password)
                .name(name)
                .surname(surname)
                .build();
        return userService.save(user);
    }

    public List<User> findAll() {
        return userService.findAll();
    }
}
