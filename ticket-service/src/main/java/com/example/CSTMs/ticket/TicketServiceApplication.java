package com.example.CSTMs.ticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class TicketServiceApplication {
    public static void main(String[] args) {
        // ✅ Load .env variables (Fix for missing environment variable issue)
        Dotenv dotenv = Dotenv.configure().directory("./").load();  
        String mongoUri = dotenv.get("MONGO_URI");

        // ✅ Debugging: Print to check if it's loaded correctly
        System.out.println("Loaded MONGO_URI: " + mongoUri);

        // ✅ Apply MongoDB URI to System Properties
        if (mongoUri != null) {
            System.setProperty("MONGO_URI", mongoUri);
        } else {
            throw new IllegalStateException("MONGO_URI is missing in .env file!");
        }

        SpringApplication.run(TicketServiceApplication.class, args);
    }
}
