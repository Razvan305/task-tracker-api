package com.example.tasktracker.controllers;

import com.example.tasktracker.dtos.TaskRequestDto;
import com.example.tasktracker.dtos.TaskResponseDto;
import com.example.tasktracker.entities.TaskStatus;
import com.example.tasktracker.repositories.TaskRepository;
import com.example.tasktracker.services.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskRestController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getAllTasks(@RequestParam(required = false) TaskStatus status) {
        List<TaskResponseDto> tasks = taskService.getAllTasks(status);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTaksById(@PathVariable Long id) {
        TaskResponseDto task = taskService.getTaskById(id);
        if(task == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(@Valid @RequestBody TaskRequestDto requestDto) {
        TaskResponseDto created = taskService.createTask(requestDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequestDto requestDto) {
        TaskResponseDto updated = taskService.updateTask(id, requestDto);
        if(updated == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponseDto> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
