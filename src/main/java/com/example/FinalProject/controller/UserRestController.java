package com.example.FinalProject.controller;

import com.example.FinalProject.exceptions.UserNotFoundException;
import com.example.FinalProject.model.UserModel;
import com.example.FinalProject.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
public class UserRestController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/api/register")
    public void register (@RequestBody @Valid UserModel inputUser) {
        userRepository.save(inputUser);
    }

    @GetMapping("/api/user/profile/{uuid}")
    public UserModel getProfile (@PathVariable UUID uuid) throws UserNotFoundException {
        return userRepository.findById(uuid).orElseThrow(() -> new UserNotFoundException(uuid));
    }

    @GetMapping("/api/getAllUsers")
    public List<UserModel> getAllEmployees() {

        return (List<UserModel>) userRepository.findAll();
    }
    @PutMapping("/api/user/profile/{uuid}")
    public void updateUser(@PathVariable UUID uuid, @RequestBody @Valid UserModel inputUser) throws UserNotFoundException {
        UserModel existingUser = userRepository.findById(uuid).orElseThrow(() -> new UserNotFoundException(uuid));
        BeanUtils.copyProperties(inputUser, existingUser, "id");
        userRepository.save(existingUser);
    }

    @PutMapping("/api/user/password/{uuid}")
    public void updatePassword(@PathVariable UUID uuid, @RequestBody @Valid String newPassword) throws UserNotFoundException {
        UserModel user = userRepository.findById(uuid).orElseThrow(() -> new UserNotFoundException(uuid));
        user.setPassword(newPassword);
        userRepository.save(user);
    }
    @DeleteMapping("/api/user/profile/{uuid}")
    public void deleteUser(@PathVariable UUID uuid) {
        userRepository.deleteById(uuid);
    }





}
