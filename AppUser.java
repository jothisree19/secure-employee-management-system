package com.example.secureemployeeapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "app_users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    // No-argument constructor
    public AppUser() {
    }

    // Getter for id
    public Long getId() {
        return id;
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Getter for role
    public String getRole() {
        return role;
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }

    // Setter for role
    public void setRole(String role) {
        this.role = role;
    }
}