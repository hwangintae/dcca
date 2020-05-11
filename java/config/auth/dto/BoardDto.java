package kr.or.dcca.dcca.config.auth.dto;

import kr.or.dcca.dcca.domain.Board;
import lombok.*;
import org.apache.tomcat.jni.Local;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Getter @Setter
@ToString
@NoArgsConstructor
public class BoardDto {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Board toEntity() {
        Board board = Board.builder()
                .id(id)
                .title(title)
                .content(content)
                .writer(writer)
                .build();
        return board;
    }

    @Builder
    public BoardDto(Long id, String title, String content, String writer, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
