package de.htwberlin.webtech.todolist.web.api;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

public class TaskCreateRequest {

    @Size(min = 1, max = 255, message = "The title must be between 1 and 255 characters long.")
    private String titel;

    @NotBlank(message = "The content must not be empty.")
    private String inhalt;

    @NotNull(message = "The due date must not be empty.")
    private Date datum;

    @NotNull(message = "The user_id must not be empty.")
    private long benutzer_id;

    public TaskCreateRequest(String titel, String inhalt, Date datum, long benutzer_id) {
        this.titel = titel;
        this.inhalt = inhalt;
        this.datum = datum;
        this.benutzer_id = benutzer_id;
    }

    public TaskCreateRequest(String titel, String inhalt, Date datum) {
        this.titel = titel;
        this.inhalt = inhalt;
        this.datum = datum;
    }

    public TaskCreateRequest() {}

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getInhalt() {
        return inhalt;
    }

    public void setInhalt(String inhalt) {
        this.inhalt = inhalt;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public long getBenutzer_id() {
        return benutzer_id;
    }

    public void setBenutzer_id(long benutzer_id) {
        this.benutzer_id = benutzer_id;
    }

}
