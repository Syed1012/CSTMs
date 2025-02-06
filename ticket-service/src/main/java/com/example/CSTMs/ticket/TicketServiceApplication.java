package com.example.CSTMs.ticket;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class TicketServiceApplication {
    public static void main(String[] args) {

        // Load .env variables
        Dotenv dotenv = Dotenv.load();
        System.setProperty("MONGO_URI", dotenv.get("MONGO_URI"));

        SpringApplication.run(TicketServiceApplication.class, args);
    }
}
