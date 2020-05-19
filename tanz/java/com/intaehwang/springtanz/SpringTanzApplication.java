package com.intaehwang.springtanz;

import com.intaehwang.springtanz.domain.Board;
import com.intaehwang.springtanz.repository.BoardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@EnableJpaAuditing
@SpringBootApplication
public class SpringTanzApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTanzApplication.class, args);
    }

}
