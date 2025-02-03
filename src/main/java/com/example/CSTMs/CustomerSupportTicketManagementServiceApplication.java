package com.example.CSTMs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CustomerSupportTicketManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerSupportTicketManagementServiceApplication.class, args);
	}

}