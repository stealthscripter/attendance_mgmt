package com.example.attendance;

public class ClassItem {
    private String className;
    private String subjectName;

    public void setClassName(String className) {
        this.className = className;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getClassName() {
        return className;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public ClassItem(String className, String subjectName) {
        this.className = className;
        this.subjectName = subjectName;
    }
}
