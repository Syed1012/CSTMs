package com.example.CSTMs.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableMongoAuditing
public class UserServiceApplication {
    public static void main(String[] args) {

        // Load .env variables
        Dotenv dotenv = Dotenv.load();
        System.setProperty("MONGO_URI", dotenv.get("MONGO_URI"));

        SpringApplication.run(UserServiceApplication.class, args);
    }
}