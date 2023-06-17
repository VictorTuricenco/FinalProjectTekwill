package com.example.FinalProject.controller;

import com.example.FinalProject.exceptions.ProjectNotFoundException;
import com.example.FinalProject.exceptions.UserNotFoundException;
import com.example.FinalProject.model.ProjectModel;
import com.example.FinalProject.model.UserModel;
import com.example.FinalProject.repository.ProjectRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ProjectRestController {
    @Autowired
    ProjectRepository projectRepository;

    @GetMapping("/api/projects")
    public List<ProjectModel> getAllProjects() {

        return (List<ProjectModel>) projectRepository.findAll();
    }
    @GetMapping("/api/projects/{projectId}")
    public ProjectModel getProject (@PathVariable UUID projectId) throws ProjectNotFoundException {
        return projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
    }

    @PostMapping("/api/projects")
    public void newProject (@RequestBody @Valid ProjectModel inputProject) {

        projectRepository.save(inputProject);
    }


    @PutMapping("/api/projects/{projectId}")
    public void updateUser(@PathVariable UUID projectId, @RequestBody @Valid ProjectModel inputProject) throws ProjectNotFoundException {
        ProjectModel existingProject = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        BeanUtils.copyProperties(inputProject, existingProject, "id");
        projectRepository.save(existingProject);
    }

    @DeleteMapping("/api/projects/{projectId}")
    public void deleteProject(@PathVariable UUID projectId) {
        projectRepository.deleteById(projectId);
    }





}
