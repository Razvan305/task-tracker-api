package com.example.tasktracker.repositories;

import com.example.tasktracker.entities.Task;
import com.example.tasktracker.entities.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(TaskStatus status);
}
