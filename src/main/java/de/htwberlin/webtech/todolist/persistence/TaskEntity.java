package de.htwberlin.webtech.todolist.persistence;

import java.sql.Date;

import javax.persistence.*;

@Entity(name = "aufgabe")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "titel")
    private String titel;

    @Column(name = "inhalt")
    private String inhalt;

    @Column(name = "datum")
    private Date datum;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "benutzer_id", nullable = false)
    private long benutzer_id;

    public TaskEntity(String titel, String inhalt, Date datum, long benutzer_id) {
        this.titel = titel;
        this.inhalt = inhalt;
        this.datum = datum;
        this.benutzer_id = benutzer_id;
    }

    protected TaskEntity(){

    }

    public long getId() {
        return id;
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
