package de.htwberlin.webtech.todolist.web;

import de.htwberlin.webtech.todolist.service.RegistrationService;
import de.htwberlin.webtech.todolist.web.api.RegistrationRequest;
import de.htwberlin.webtech.todolist.web.api.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Void> register(@RequestBody RegistrationRequest request) {
        var valid = validate(request);
        if(!valid) return ResponseEntity.badRequest().build();
        var isUserCreated = registrationService.register(request);
        if(!isUserCreated) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/api/v1/registration")
    @ResponseStatus(code = HttpStatus.OK)
    public List<User> getRegisteredUsers() {
        return registrationService.findAll();
    }

    private boolean validate(RegistrationRequest request) {
        return request.getVorname() != null
                && !request.getVorname().isBlank()
                && request.getNachname() != null
                && !request.getNachname().isBlank()
                && request.getEmail() != null
                && !request.getEmail().isBlank()
                && request.getPasswort() != null
                && !request.getPasswort().isBlank();
    }
}
