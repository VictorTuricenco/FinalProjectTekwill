package com.example.FinalProject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "PROJECTS")


public class ProjectModel {

    @Id
    @Column
    @GeneratedValue
    private UUID id;

    @NotEmpty
    @Column
    private String name;
    @NotEmpty
    @Column
    private String description;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column
    private Date start;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column
    private Date finish;
    @OneToMany
    @JoinColumn (name ="tasksm_id")
    private List<TasksModel> tasks;

    public List<TasksModel> getTasks() {
        return tasks;
    }

    public void setTasks(List<TasksModel> tasks) {
        this.tasks = tasks;
    }
    public ProjectModel(UUID id, String name, String description, Date start, Date finish, List<TasksModel> tasks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.start = start;
        this.finish = finish;
        this.tasks= tasks;
    }

    public ProjectModel() {
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }
}


