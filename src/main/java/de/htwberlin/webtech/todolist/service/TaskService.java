package de.htwberlin.webtech.todolist.service;

import antlr.Token;
import com.nimbusds.jwt.JWTParser;
import de.htwberlin.webtech.todolist.persistence.TaskEntity;
import de.htwberlin.webtech.todolist.persistence.TaskRepository;
import de.htwberlin.webtech.todolist.web.api.Task;
import de.htwberlin.webtech.todolist.web.api.TaskCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;
    private final TokenService tokenService;

    public TaskService(TaskRepository taskRepository, UserService userService, TokenService tokenService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    public List<Task> findAll(){
        List<TaskEntity> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Task create(TaskCreateRequest req){
        var taskEntity = new TaskEntity(req.getTitel(), req.getInhalt(), req.getDatum(), userService.findByIdRaw(req.getBenutzer_id()));
        taskRepository.save(taskEntity);
        return transformEntity(taskEntity);
    }

    public Task findById(Long id){
        var taskEntity = taskRepository.findById(id);
        return taskEntity.map(this::transformEntity).orElse(null);
    }

    public Task update(Long id, TaskCreateRequest req){
        var taskEntityOptional = taskRepository.findById(id);
        if(taskEntityOptional.isEmpty()) return null;
        var taskEntity = taskEntityOptional.get();
        taskEntity.setTitel(req.getTitel());
        taskEntity.setInhalt(req.getInhalt());
        taskEntity.setDatum(req.getDatum());
        taskEntity = taskRepository.save(taskEntity);
        return transformEntity(taskEntity);
    }

    public ResponseEntity<Void> deleteById(Long id){
        if(!taskRepository.existsById(id)) return ResponseEntity.notFound().build();
        taskRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public List<Task> findByToken(String token){
        try {
            var userMail = tokenService.decodeToken(token);
            Long userId = userService.findIdByEmail(userMail);
            var taskEntity = taskRepository.findAllByBenutzer(userId);
            return taskEntity.stream()
                    .map(this::transformEntity)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Task createByToken(String token, TaskCreateRequest req){
        try {
            var userMail = tokenService.decodeToken(token);
            Long userId = userService.findIdByEmail(userMail);
            var taskEntity = new TaskEntity(req.getTitel(), req.getInhalt(), req.getDatum(), userService.findByIdRaw(userId));
            taskRepository.save(taskEntity);
            return transformEntity(taskEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Task transformEntity(TaskEntity taskEntity){
        return new Task(
                taskEntity.getId(),
                taskEntity.getTitel(),
                taskEntity.getInhalt(),
                taskEntity.getDatum(),
                taskEntity.getBenutzer()
        );
    }
}
