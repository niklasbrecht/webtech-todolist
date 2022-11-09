package de.htwberlin.webtech.todolist.web.api;

public class RegistrationRequest {

    private final String vorname;
    private final String nachname;
    private final String email;
    private final String passwort;

    public RegistrationRequest(String vorname, String nachname, String email, String passwort) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.passwort = passwort;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswort() {
        return passwort;
    }
}
