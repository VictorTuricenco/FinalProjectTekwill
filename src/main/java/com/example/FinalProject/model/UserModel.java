package com.example.FinalProject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

@Entity
@Table (name = "USERS")

public class UserModel {

    @Id
    @Column
    @GeneratedValue
    private UUID id;

    @NotEmpty
    @Column
    private String name;
    @NotEmpty
    @Column
    private String surname;
    @NotEmpty
    @Column
    private String email;
    @NotEmpty
    @Column
    private String password;

    public UserModel(UUID id, String name, String surname, String email, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public UserModel() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

