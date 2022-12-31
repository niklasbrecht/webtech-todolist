package de.htwberlin.webtech.todolist.persistence;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskEntityTest {

    @Test
    void testToString() {
        // Setup
        final UserEntity userEntity = new UserEntity("vname", "nname", "email", "passwort");
        final TaskEntity taskEntity = new TaskEntity("title", "content", Date.valueOf(LocalDate.now()), userEntity);

        // Expected result
        final String expected = "TaskEntity{titel='title', inhalt='content', datum=" + Date.valueOf(LocalDate.now()) + ", benutzer=UserEntity{vorname='vname', nachname='nname', email='email', passwort='passwort', userRole=USER}}";

        // Actual result
        final String actual = taskEntity.toString();

        // Verify the results
        assertEquals(expected, actual);
    }
}
