package com.example.FinalProject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.UUID;

enum Status {
    IN_PROGRESS ("IN PROGRESS"),
    DONE ("DONE");

    private final String value;
    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}


@Entity
@Table(name = "TASKS")

public class TasksModel {

    @Id
    @Column
    @GeneratedValue
    private UUID id;

    @NotEmpty
    @Column
    private String name;

    @Column
    private String description;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column
    private Date deadline;

    @Column
    private Status status;
    @Column
    private boolean completed;

    @ManyToOne
    @JoinColumn (name ="project_id")
    private ProjectModel project;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private UserModel user;

    public TasksModel(UUID id, String name, String description, Date deadline, Status status, boolean competed, ProjectModel project, UserModel user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
        this.completed = competed;
        this.project = project;
        this.user = user;
    }

    public TasksModel() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public ProjectModel getProject() {
        return project;
    }

    public void setProject(ProjectModel project) {
        this.project = project;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }



}
