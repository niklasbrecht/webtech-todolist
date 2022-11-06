package de.htwberlin.webtech.todolist.web.api;

import java.sql.Date;

import de.htwberlin.webtech.todolist.persistence.UserEntity;

public class Task {

    private long id;
    private String titel;
    private String inhalt;
    private Date datum;
    private UserEntity benutzer;

    public Task(long id, String titel, String inhalt, Date datum, UserEntity benutzer) {
        this.id = id;
        this.titel = titel;
        this.inhalt = inhalt;
        this.datum = datum;
        this.benutzer = benutzer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public UserEntity getBenutzer() {
        return this.benutzer;         //prints out on page
    }
    
}
