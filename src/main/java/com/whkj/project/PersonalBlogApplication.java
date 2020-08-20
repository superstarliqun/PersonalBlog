package com.whkj.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.whkj.project.mapper")
public class PersonalBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalBlogApplication.class, args);
    }

}
