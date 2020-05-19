package com.intaehwang.springtanz.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class Subject {

    @Id @GeneratedValue
    private Long id;

    private String subjectName;
    private String subjectStart;
    private String subjectEnd;
    private String teacherId;
    private String bookName;

    @OneToMany
    private List<Exam> examList;
}
