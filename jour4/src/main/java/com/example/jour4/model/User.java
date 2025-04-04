package com.example.jour4.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // important : "users" car "user" est réservé en SQL
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role; // ex: "ROLE_ADMIN", "ROLE_USER"

    public User() {}

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters et setters
    public Long getId() { return id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }
}