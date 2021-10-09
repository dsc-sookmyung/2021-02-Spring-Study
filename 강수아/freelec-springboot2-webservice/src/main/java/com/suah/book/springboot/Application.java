package com.suah.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Application {   //프로젝트의 메인 class
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
