package com.jason.springbootjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SpringBootJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJwtApplication.class, args);
    }

}
