package com.example.myapplication6;

// Clasa realizeaza interfata Serializable
import java.io.Serializable;

public class User implements Serializable {

    private String name;
    private String company;
    private int age;

    public User(String name, String company, int age){
        this.name = name;
        this.company = company;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}