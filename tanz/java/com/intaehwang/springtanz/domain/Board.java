package com.intaehwang.springtanz.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Board extends Time{

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private String writer;

    @Builder
    public Board(Long id, String title, String content, String writer) {
        this.id = id;

        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
