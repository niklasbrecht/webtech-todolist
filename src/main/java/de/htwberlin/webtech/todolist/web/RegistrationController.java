package de.htwberlin.webtech.todolist.web;

import de.htwberlin.webtech.todolist.service.RegistrationService;
import de.htwberlin.webtech.todolist.web.api.RegistrationRequest;
import de.htwberlin.webtech.todolist.web.api.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping(path = "/api/v2/registration")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "/api/v1/registration")
    @ResponseStatus(code = HttpStatus.OK)
    public List<User> getRegisteredUsers() {
        return registrationService.findAll();
    }
}
