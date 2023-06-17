package com.example.FinalProject.exceptions;

import java.util.UUID;

public class TaskNotFoundException extends Exception{
    public TaskNotFoundException(UUID id) {
        super("Task not found with id: " + id);
    }
}
