package de.htwberlin.webtech.todolist.web;

import de.htwberlin.webtech.todolist.service.RegistrationService;
import de.htwberlin.webtech.todolist.web.api.RegistrationRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }
}
