package com.example.redisstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class RedisStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisStudyApplication.class, args);
    }

}
