package com.example.hamecobooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HamecoBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(HamecoBookingApplication.class, args);
    }

}
