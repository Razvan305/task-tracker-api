package com.example.tasktracker.mappers;

import com.example.tasktracker.dtos.TaskRequestDto;
import com.example.tasktracker.dtos.TaskResponseDto;
import com.example.tasktracker.entities.Task;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskResponseDto toResponseDto(Task task);

    List <TaskResponseDto> toResponseDtoList(List<Task> tasks);

    TaskRequestDto toRequestDto(Task task);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Task toEntity(TaskRequestDto Dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(TaskRequestDto dto, @MappingTarget Task task);
}
