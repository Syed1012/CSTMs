package com.example.CSTMs.user.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id; 
import lombok.*;

@Document(collection = "users") // This tells Spring Data to store our objects in the users collection in MongoDB
@Data // Lombok annotation to create all the getters, setters, equals, hash, and toString methods based on the fields
@NoArgsConstructor // Lombok annotation to create a no-args constructor
@AllArgsConstructor // Lombok annotation to create a constructor with all the fields
@Builder // Lombok annotation to create a builder so you can build objects like: User.builder().id("1").name("John").build();
public class User {

    @Id
    private String id;

    private String userName;

    private String email;

    private String password;

    private int role; // 0: Admin, 1: Agent, 2: Customer

    @CreatedDate
    private LocalDateTime createdAt;

}
