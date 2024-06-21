package com.example.attendance;

public class StudentItem {
    private String roll;
    private String name;
    private String status;
    public StudentItem(String roll, String name) {
        this.roll = roll;
        this.name = name;
        status="";
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoll() {
        return roll;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }
}
