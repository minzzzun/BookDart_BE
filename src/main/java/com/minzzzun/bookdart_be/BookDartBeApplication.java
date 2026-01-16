package com.minzzzun.bookdart_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BookDartBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookDartBeApplication.class, args);
    }

}
