package de.htwberlin.webtech.todolist.service;

import de.htwberlin.webtech.todolist.persistence.TaskEntity;
import de.htwberlin.webtech.todolist.persistence.TaskRepository;
import de.htwberlin.webtech.todolist.web.api.Task;
import de.htwberlin.webtech.todolist.web.api.TaskCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll(){
        List<TaskEntity> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Task create(TaskCreateRequest req){
        var taskEntity = new TaskEntity(req.getTitel(), req.getInhalt(), req.getDatum());
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

    public boolean deleteById(Long id){
        if(!taskRepository.existsById(id)) return false;
        taskRepository.deleteById(id);
        return true;
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
