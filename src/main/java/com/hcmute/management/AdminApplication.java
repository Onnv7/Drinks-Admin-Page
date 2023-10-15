package com.hcmute.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableMongoAuditing
@Slf4j
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
        log.info("Server is running on http://localhost:3000");
    }
}