package de.htwberlin.webtech.todolist.web.api;

import java.sql.Date;

public class Task {

    private long id;
    private String titel;
    private String inhalt;
    private Date datum;
    private long benutzer_id;

    public Task(long id, String titel, String inhalt, Date datum, long benutzer_id) {
        this.id = id;
        this.titel = titel;
        this.inhalt = inhalt;
        this.datum = datum;
        this.benutzer_id = benutzer_id;
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

    public long getBenutzer_id() {
        return benutzer_id;
    }

    public void setBenutzer_id(long benutzer_id) {
        this.benutzer_id = benutzer_id;
    }
    
}
