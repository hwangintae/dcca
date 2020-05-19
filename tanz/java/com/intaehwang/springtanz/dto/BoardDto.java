package com.intaehwang.springtanz.dto;

import com.intaehwang.springtanz.domain.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
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