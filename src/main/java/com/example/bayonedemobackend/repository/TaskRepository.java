package com.example.bayonedemobackend.repository;

import com.example.bayonedemobackend.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Tasks, Integer> {

    //Get All Tasks
    @Query(value = "SELECT e.* FROM Tasks e", nativeQuery = true)
    List<Tasks> getAllTasks();

    //Get All From email
    @Query(value = "SELECT e.* FROM Tasks e WHERE e.email=?1", nativeQuery = true)
    List<Tasks> getAllTasksFilteredByEmail(String email);



}