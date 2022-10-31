package de.htwberlin.webtech.todolist.web;


import de.htwberlin.webtech.todolist.persistence.TaskRepository;
import de.htwberlin.webtech.todolist.service.TaskService;
import de.htwberlin.webtech.todolist.web.api.Task;
import de.htwberlin.webtech.todolist.web.api.TaskCreateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class TaskRestController {

    private final TaskService taskService;

    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(path = "/api/v1/tasks")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Task> fetchTasks(){
        return taskService.findAll();
    }

    @GetMapping(path = "/api/v1/tasks/{id}")
    public ResponseEntity<Task> fetchTaskById(@PathVariable Long id){
        var task = taskService.findById(id);
        return task != null? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/api/v1/tasks")
    public ResponseEntity<Void> createTask(@RequestBody TaskCreateRequest request) throws URISyntaxException {
       var task = taskService.create(request);
       URI uri = new URI("/api/v1/tasks/" + task.getId());
       return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/api/v1/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskCreateRequest request){
        var task = taskService.update(id, request);
        return task != null? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/api/v1/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        boolean successful = taskService.deleteById(id);
        return successful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
