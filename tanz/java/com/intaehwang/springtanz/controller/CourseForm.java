package com.intaehwang.springtanz.controller;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class CourseForm {
    private String id;
    private String courseName;
    private String courseStart;
    private String courseEnd;
    private int createSubjectNum;
    private int joinNum;
    private int grade;
}
