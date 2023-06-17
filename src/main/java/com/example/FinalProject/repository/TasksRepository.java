package com.example.FinalProject.repository;

import com.example.FinalProject.model.TasksModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TasksRepository extends CrudRepository <TasksModel, UUID> {

    List<TasksModel> findByProjectId(UUID projectId);
    TasksModel findOneByProjectIdAndId(UUID projectId, UUID taskId);

    List<TasksModel> findByUserId(UUID userId);


}
