package com.example.CSTMs.ticket; // ✅ Match this with your main class

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TicketServiceApplication.class) // ✅ Explicitly specify the application class
class TicketServiceApplicationTests {

    @Test
    void contextLoads() {
        // Test passes if Spring Boot loads the context properly
    }
}
