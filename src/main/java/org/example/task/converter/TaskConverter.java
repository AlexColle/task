package org.example.task.converter;

import jakarta.persistence.Converter;
import org.example.task.dto.TaskDto;
import org.example.task.entity.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class TaskConverter {
    public TaskDto convertTaskToTaskDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setNome(task.getNome());
        taskDto.setDescrizione(task.getDescrizione());
        taskDto.setStato(task.getStato());
        return taskDto;
    }

    public List<TaskDto> convertTaskDtoToTask(List<Task> taskList) {
        List<TaskDto> taskDtos = new ArrayList<>();
        for (Task task : taskList) {
            TaskDto taskDto = convertTaskToTaskDto(task);
            taskDtos.add(taskDto);
        }
        return taskDtos;
    }

//    public Task convertTaskDtoToEntity (TaskDto taskDto) {
//        Task task = new Task();
//        task.setId(taskDto.getId());
//        task.setNome(taskDto.getNome());
//        task.setDescrizione(taskDto.getDescrizione());
//        task.setStato(taskDto.getStato());
//        return task;
//    }
}
