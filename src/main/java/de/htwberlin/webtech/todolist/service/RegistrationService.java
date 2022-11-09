package de.htwberlin.webtech.todolist.service;

import de.htwberlin.webtech.todolist.persistence.UserEntity;
import de.htwberlin.webtech.todolist.persistence.UserRole;
import de.htwberlin.webtech.todolist.security.MailValidator;
import de.htwberlin.webtech.todolist.web.api.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UserService userService;
    private final MailValidator mailValidator;

    public RegistrationService(UserService userService, MailValidator mailValidator) {
        this.userService = userService;
        this.mailValidator = mailValidator;
    }

    public String register(RegistrationRequest request) {
        boolean isMailValid = mailValidator.test(request.getEmail());
        if(!isMailValid) throw new IllegalStateException("Keine gütlige Mail-Adresse!");
        userService.signUpUser(
                new UserEntity(
                        request.getVorname(),
                        request.getNachname(),
                        request.getEmail(),
                        request.getPasswort(),
                        UserRole.USER
                )
        );
        return "Success";
    }
}
