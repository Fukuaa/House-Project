package com.example.houseproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.example.houseproject.Mapper")
public class HouseProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(HouseProjectApplication.class, args);
    }

}
