package kr.or.dcca.dcca;

import kr.or.dcca.dcca.domain.Board;
import kr.or.dcca.dcca.repository.BoardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootApplication
public class DccaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DccaApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(BoardRepository boardRepository) throws Exception {
        return (args) -> {
            IntStream.rangeClosed(1, 50).forEach(index ->
                    boardRepository.save(Board.builder()
                            .title("게시글" + index)
                            .content("내용" + index)
                            .createdDate(LocalDateTime.now())
                            .updateDate(LocalDateTime.now()).build()));
        };
    }
}
