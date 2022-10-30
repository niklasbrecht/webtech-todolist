package de.htwberlin.webtech.todolist.web;


import de.htwberlin.webtech.todolist.web.api.Task;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskRestController {

    private List<Task> Tasks;

    public TaskRestController(List<Task> tasks) {
        Tasks = tasks;
        Tasks.add(new Task(1, "Klausur", "Mathe", 2022, 11, 12));
        Tasks.add(new Task(2, "Referat", "Deutsch", 2022, 12, 2));
    }

    @GetMapping(path = "/api/v1/task")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Task> fetchTasks(){
        return  Tasks;
    }

}
