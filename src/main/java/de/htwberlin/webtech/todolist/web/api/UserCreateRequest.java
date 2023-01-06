package de.htwberlin.webtech.todolist.web.api;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserCreateRequest {

    @NotBlank(message = "The name must not be empty.")
    private String vorname;

    @NotBlank(message = "The surname must not be empty.")
    private String nachname;

    @Email(message = "The email must be a valid email address.")
    private String email;

    @NotBlank(message = "The password must not be empty.")
    private String passwort;

    public UserCreateRequest(String vorname, String nachname, String email, String passwort) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.passwort = passwort;
    }

    public UserCreateRequest() {}

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
}
