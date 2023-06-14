package com.example.models;

import java.io.Serializable;

public class Student implements Serializable {
    String StudentId;
    String StudentName;
    String StudentClass;

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getStudentClass() {
        return StudentClass;
    }

    public void setStudentClass(String studentClass) {
        StudentClass = studentClass;
    }

    public Student(String studentId, String studentName, String studentClass) {
        this.StudentId = studentId;
        this.StudentName = studentName;
        this.StudentClass = studentClass;
    }

    @Override
    public String toString() {
        return "Tên sinh viên: " + StudentName + '\n' + "Tên lớp: " + StudentClass + '\n' + "Mã sinh viên: " + StudentId ;

    }
}
