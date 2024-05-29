package org.example.task.controller;

import org.example.task.dto.TaskDto;
import org.example.task.entity.Task;
import org.example.task.service.TaskNotFoundException;
import org.example.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")

public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/salva")
    public Task salva(@RequestBody Task task) {
        return taskService.creaTask(task);
    }

    @GetMapping("/cercaPerId/{id}")
    public Task cercaPerId(@PathVariable int id) {
        return taskService.cercaTask(id);
    }

    @GetMapping("/listaTask")
    public List<TaskDto> listaTask() {
        return taskService.listaTaskDto();
    }

    @GetMapping("/cercaTuttiTask")
    public List<Task> cercaTuttiTask() {
        return taskService.listaTask();
    }

    @PutMapping("/aggiorna/{id}")
    public ResponseEntity<Task> aggiorna(@PathVariable int id, @RequestBody Task taskDetails) {
        try {
            Task aggiornaTasks = taskService.aggiornaTask(id, taskDetails);
            return new ResponseEntity<>(aggiornaTasks, HttpStatus.OK);
        } catch (TaskNotFoundException e) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/elimina/{id}")
    public ResponseEntity<Void> elimina(@PathVariable int id) {
        try {
            taskService.eliminaTask(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (TaskNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
