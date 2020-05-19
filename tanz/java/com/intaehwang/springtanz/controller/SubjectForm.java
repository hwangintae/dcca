package com.intaehwang.springtanz.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SubjectForm {
    private String yearMonth;
    private String subjectName;
    private int subjectGrade;
    private String teacherCode;
}
