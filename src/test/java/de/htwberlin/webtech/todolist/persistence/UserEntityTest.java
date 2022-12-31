package de.htwberlin.webtech.todolist.persistence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserEntityTest {

    @Test
    void testToString() {
        // Setup
        final UserEntity userEntity = new UserEntity("vname", "nname", "email", "passwort");

        // Expected result
        final String expected = "UserEntity{vorname='vname', nachname='nname', email='email', passwort='passwort', userRole=USER}";

        // Actual result
        final String actual = userEntity.toString();

        // Verify the results
        assertEquals(expected, actual);
    }


    @Test
    void testToStringWithAdminRole() {
        // Setup
        final UserEntity userEntity = new UserEntity("vname", "nname", "email", "passwort", UserRole.ADMIN);

        // Expected result
        final String expected = "UserEntity{vorname='vname', nachname='nname', email='email', passwort='passwort', userRole=ADMIN}";

        // Actual result
        final String actual = userEntity.toString();

        // Verify the results
        assertEquals(expected, actual);
    }
}
