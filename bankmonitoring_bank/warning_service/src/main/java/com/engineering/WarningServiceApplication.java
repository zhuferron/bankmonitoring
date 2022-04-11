package com.engineering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WarningServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarningServiceApplication.class, args);
    }

}
