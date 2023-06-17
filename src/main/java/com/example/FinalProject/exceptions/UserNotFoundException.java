package com.example.FinalProject.exceptions;

import java.util.UUID;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(UUID id) {
        super("User not found with id: " + id);
    }
}
