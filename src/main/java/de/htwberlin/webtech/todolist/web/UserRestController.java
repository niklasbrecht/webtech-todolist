package de.htwberlin.webtech.todolist.web;

import de.htwberlin.webtech.todolist.service.UserService;
import de.htwberlin.webtech.todolist.web.api.TaskCreateRequest;
import de.htwberlin.webtech.todolist.web.api.User;
import de.htwberlin.webtech.todolist.web.api.UserCreateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    // Since V1-Endpoints are deprecated, the requests do not have to be validated

    @GetMapping(path = "/api/v1/user")
    @ResponseStatus(code = HttpStatus.OK)
    public List<User> fetchUsers(){
        return userService.findAll();
    }

    @GetMapping(path = "/api/v1/user/{id}")
    public ResponseEntity<User> fetchUserById(@PathVariable Long id){
        var user = userService.findById(id);
        return user != null? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/api/v1/user")
    public ResponseEntity<Void> createUser(@RequestBody UserCreateRequest request) throws URISyntaxException {
       var user = userService.create(request);
       URI uri = new URI("/api/v1/user/" + user.getId());
       return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/api/v1/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserCreateRequest request){
        var user = userService.update(id, request);
        return user != null? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/api/v1/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        boolean successful = userService.deleteById(id);
        return successful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
