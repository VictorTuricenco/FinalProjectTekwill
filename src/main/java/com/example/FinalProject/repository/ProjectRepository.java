package com.example.FinalProject.repository;

import com.example.FinalProject.model.ProjectModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository extends CrudRepository <ProjectModel, UUID> {

}
