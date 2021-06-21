package com.jlheidemann.carapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Car API", version = "1.0", description = "Car consumption rank API"))
public class CarApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarApiApplication.class, args);
    }

}
