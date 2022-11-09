package de.htwberlin.webtech.todolist.security;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class MailValidator implements Predicate<String> {

    @Override
    public boolean test(String mail) {
        // TODO: Regex for Mail
        return true;
    }
}
