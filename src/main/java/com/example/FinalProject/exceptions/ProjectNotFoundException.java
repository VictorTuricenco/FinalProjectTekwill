package com.example.FinalProject.exceptions;

import java.util.UUID;

public class ProjectNotFoundException extends Exception{

    public ProjectNotFoundException(UUID id) {
        super("Project not found with id: " + id);
    }
}
