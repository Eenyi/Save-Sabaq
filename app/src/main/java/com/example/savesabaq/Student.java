package com.example.savesabaq;

public class Student {
    private Integer rollNumber;
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public Student(Integer rollNumber, String name) {
        this.name = name;
        this.rollNumber = rollNumber;
    }

    public Integer getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(Integer rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return (" رول نمبر  :  " + rollNumber + "   -   " + name) ;
    }
}
