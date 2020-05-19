package com.intaehwang.springtanz.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Course {

    @Id
    private String id;
    private String courseName;
    private String courseStart;
    private String courseEnd;
    private int createSubjectNum;
    private int joinNum;
    private int grade;

    @OneToMany
    private List<Subject> subject = new ArrayList<>();

    @Builder
    public Course(String courseName, String courseStart,
                  String courseEnd, int createSubjectNum, int joinNum, int grade) {
        this.courseName = courseName;
        this.courseStart = courseStart;
        this.courseEnd = courseEnd;
        this.createSubjectNum = createSubjectNum;
        this.joinNum = joinNum;
        this.grade = grade;
    }
}
