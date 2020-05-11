package kr.or.dcca.dcca;

import kr.or.dcca.dcca.domain.Board;
import kr.or.dcca.dcca.repository.BoardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@EnableJpaAuditing
@SpringBootApplication
public class DccaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DccaApplication.class, args);
    }
}
