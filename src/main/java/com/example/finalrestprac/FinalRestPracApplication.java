package com.example.finalrestprac;

import com.example.finalrestprac.properties.FileStorageProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({FileStorageProperty.class})
@SpringBootApplication
public class FinalRestPracApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalRestPracApplication.class, args);
    }

}
