package de.htwberlin.webtech.todolist.service;

import de.htwberlin.webtech.todolist.persistence.*;
import de.htwberlin.webtech.todolist.security.MailValidator;
import de.htwberlin.webtech.todolist.web.api.RegistrationRequest;
import de.htwberlin.webtech.todolist.web.api.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    private final UserService userService;
    private final MailValidator mailValidator;
    private final UserRepository userRepository;


    public RegistrationService(UserService userService, MailValidator mailValidator, UserRepository userRepository) {
        this.userService = userService;
        this.mailValidator = mailValidator;
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        List<UserEntity> user = userRepository.findAll();
        return user.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }
    public boolean register(RegistrationRequest request) {
        boolean isMailValid = mailValidator.test(request.getEmail());
        if(!isMailValid) return false;
        userService.signUpUser(
                new UserEntity(
                        request.getVorname(),
                        request.getNachname(),
                        request.getEmail(),
                        request.getPasswort(),
                        UserRole.USER
                )
        );
        return true;
    }

    private User transformEntity(UserEntity userEntity){
        return new User(
                userEntity.getId(),
                userEntity.getVorname(),
                userEntity.getNachname(),
                userEntity.getEmail(),
                userEntity.getPasswort()
        );
    }
}
