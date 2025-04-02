package com.example.jour2.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class UserForm {

    @NotBlank(message = "Le pseudo est requis")
    private String pseudo;

    @NotNull(message = "L'âge est requis")
    @Min(value = 1, message = "L'âge doit être supérieur à 0")
    private Integer age;

    // Getters & Setters
    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}