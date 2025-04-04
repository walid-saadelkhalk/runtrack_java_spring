package com.example.jour4.dto;

public class PersonDTO {
    private String name;
    private int age;

    public PersonDTO() {}

    public PersonDTO(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters & Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
