package org.example.task.service;

import org.example.task.converter.TaskConverter;
import org.example.task.dto.TaskDto;
import org.example.task.entity.Task;
import org.example.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskConverter taskConverter;

    public Task creaTask(Task task) {
        return taskRepository.save(task);
    }

    public Task cercaTask(int id) {
        return taskRepository.findById(id).get();
    }

    public Task aggiornaTask(int id, Task taskDetails) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new TaskNotFoundException ("Task non trovato con ID: " + id);
        }
        Task task = optionalTask.get();

        task.setNome(taskDetails.getNome());
        task.setDescrizione(taskDetails.getDescrizione());
        task.setStato(taskDetails.getStato());

        try {
            return taskRepository.save(task);
        } catch (DataIntegrityViolationException e) {
            throw new TaskNotFoundException ("Impossibile aggiornare il task con ID: " + id);
        }

    }

    public List<Task> listaTask() {
        return taskRepository.findAll();
    }

    public void eliminaTask(int id) {
        taskRepository.deleteById(id);
    }

    public List<TaskDto> listaTaskDto(){
        return taskConverter.convertTaskDtoToTask(taskRepository.findAll());
    }
}
