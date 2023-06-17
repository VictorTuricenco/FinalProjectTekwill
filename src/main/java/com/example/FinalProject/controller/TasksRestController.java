package com.example.FinalProject.controller;

import com.example.FinalProject.exceptions.ProjectNotFoundException;
import com.example.FinalProject.exceptions.TaskNotFoundException;
import com.example.FinalProject.exceptions.UserNotFoundException;
import com.example.FinalProject.model.ProjectModel;
import com.example.FinalProject.model.TasksModel;
import com.example.FinalProject.model.UserModel;
import com.example.FinalProject.repository.ProjectRepository;
import com.example.FinalProject.repository.TasksRepository;
import com.example.FinalProject.repository.UserRepository;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class TasksRestController {

    @Autowired
    TasksRepository tasksRepository;
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/api/tasks")
    public List<TasksModel> getAllTasks() {
        return (List<TasksModel>) tasksRepository.findAll();
    }

    @GetMapping ("/api/projects/{projectId}/tasks")
    public List<TasksModel> getAllTasksByProjectId (@PathVariable UUID projectId) throws ProjectNotFoundException {
        if (!projectRepository.existsById(projectId)) {
            throw new ProjectNotFoundException(projectId);
        }
        return tasksRepository.findByProjectId(projectId);

    }

    @GetMapping ("/api/projects/{projectId}/{taskId}")
    public TasksModel getTasksByProjectId (@PathVariable UUID projectId, @PathVariable UUID taskId) throws ProjectNotFoundException, TaskNotFoundException {
        if (!projectRepository.existsById(projectId)) {
            throw new ProjectNotFoundException(projectId);
        }
        if (!tasksRepository.existsById(taskId)) {
            throw new TaskNotFoundException(taskId);
        }

        return tasksRepository.findOneByProjectIdAndId(projectId, taskId);

    }
    @PostMapping("/api/projects/{projectId}/tasks")
    public TasksModel newTask (@PathVariable UUID projectId, @RequestBody @Valid TasksModel inputTask) throws ProjectNotFoundException {
        ProjectModel project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        inputTask.setProject(project);
        return tasksRepository.save(inputTask);

    }

    @PutMapping("/api/projects/{projectId}/{taskId}")
    public TasksModel updatePerson(@RequestBody TasksModel inputTask, @PathVariable UUID projectId, @PathVariable UUID taskId) throws TaskNotFoundException {
        TasksModel existingTask = tasksRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(projectId));
        existingTask.setName(inputTask.getName());
        existingTask.setDescription(inputTask.getDescription());
        existingTask.setDeadline(inputTask.getDeadline());
        existingTask.setStatus(inputTask.getStatus());

        return tasksRepository.save(existingTask);
    }

    @DeleteMapping("/api/projects/{projectId}/tasks/{taskId}")
    public void deleteTask(@PathVariable UUID projectId, @PathVariable UUID taskId) {
        tasksRepository.deleteById(taskId);
    }

    @GetMapping ("/api/tasks/assigned/{userId}")
    public List<TasksModel> getAllTasksByUserId (@PathVariable UUID userId) throws UserNotFoundException {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        return tasksRepository.findByUserId(userId);

    }

    @PutMapping("/api/tasks/{taskId}/assign/{userId}")
    public void assignTaskToUser(@PathVariable UUID taskId, @PathVariable UUID userId) throws TaskNotFoundException, UserNotFoundException {
        TasksModel task = tasksRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        task.setUser(user);
        tasksRepository.save(task);

    }

    @PutMapping("/api/tasks/{taskId}/complete")
    public void markTaskAsCompleted(@PathVariable UUID taskId) throws TaskNotFoundException {
        TasksModel task = tasksRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
        task.setCompleted(true);
        tasksRepository.save(task);
    }



}
