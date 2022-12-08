package de.htwberlin.webtech.todolist.web;


import com.google.common.base.Strings;
import com.nimbusds.jose.shaded.json.JSONObject;
import de.htwberlin.webtech.todolist.service.TaskService;
import de.htwberlin.webtech.todolist.service.TokenService;
import de.htwberlin.webtech.todolist.service.UserService;
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

    public TaskRestController(TaskService taskService, TokenService tokenService, UserService userService) {
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
        return taskService.deleteById(id);
    }

    @GetMapping(path = "/api/v2/tasks")
    public ResponseEntity<List<Task>> fetchUserTasks(@RequestHeader("Authorization") String authHeader){
        if(Strings.isNullOrEmpty(authHeader) || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<Task> tasks = taskService.findByToken(authHeader.substring(7));
        if(tasks == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping(path = "/api/v2/tasks")
    public ResponseEntity<?> createTask(@RequestHeader("Authorization") String authHeader,
                                           @RequestBody TaskCreateRequest req) throws URISyntaxException {
        if(Strings.isNullOrEmpty(authHeader) || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        var task = taskService.createByToken(authHeader.substring(7), req);
        URI uri = new URI("/api/v2/tasks/" + task.getId());
        return new ResponseEntity<String>(Long.toString(task.getId()), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/api/v2/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@RequestHeader("Authorization") String authHeader, @PathVariable Long id){
        if(Strings.isNullOrEmpty(authHeader) || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return taskService.deleteById(id);
    }
}
