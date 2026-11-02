package com.example.tasktracker.services;

import com.example.tasktracker.dtos.TaskRequestDto;
import com.example.tasktracker.dtos.TaskResponseDto;
import com.example.tasktracker.entities.Task;
import com.example.tasktracker.entities.TaskStatus;
import com.example.tasktracker.mappers.TaskMapper;
import com.example.tasktracker.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public List<TaskResponseDto> getAllTasks(TaskStatus status) {
        List<Task> tasks = (status != null)
                ? taskRepository.findByStatus(status)
                : taskRepository.findAll();
        return taskMapper.toResponseDtoList(tasks);
    }

    public TaskResponseDto getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElse(null);
        return taskMapper.toResponseDto(task);
    }

    public TaskResponseDto createTask(TaskRequestDto requestDto) {
        if (requestDto.getStatus() == null) {
            requestDto.setStatus(TaskStatus.PENDING);
        }
        Task task = taskMapper.toEntity(requestDto);
        Task savedTask = taskRepository.save(task);
        return taskMapper.toResponseDto(savedTask);
    }

    public TaskResponseDto updateTask(Long id, TaskRequestDto requestDto) {
        Task task = taskRepository.findById(id)
                .orElse(null);
        taskMapper.updateEntityFromDto(requestDto, task);
        Task updatedTask = taskRepository.save(task);
        return taskMapper.toResponseDto(updatedTask);
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }
}
