package com.bilgeadam.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_user")
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name= "email",unique = true,nullable = false)
        private String email;

        @Column(name= "password",nullable = false)
        private String password;
        private String name;
        private String surname;
        private String profilePhotoUrl;
        @Embedded
        private BaseEntity baseEntity;
}
