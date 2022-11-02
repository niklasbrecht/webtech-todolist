package de.htwberlin.webtech.todolist.web.api;

import java.sql.Date;

public class TaskCreateRequest {

    private String titel;
    private String inhalt;
    private Date datum;
    private long benutzer_id;

    public TaskCreateRequest(String titel, String inhalt, Date datum, long benutzer_id) {
        this.titel = titel;
        this.inhalt = inhalt;
        this.datum = datum;
        this.benutzer_id = benutzer_id;
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
