package com.example.softlearning;

import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SoftlearningApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SoftlearningApplication.class, args);

        System.out.println("\n*****   A P P L I C A T I O N    S T A R T E D   *****\n");

		
	}

}
