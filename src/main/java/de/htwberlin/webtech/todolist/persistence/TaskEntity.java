package de.htwberlin.webtech.todolist.persistence;

import java.sql.Date;

import javax.persistence.*;

@Entity(name = "tasks")
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

    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id")
    private UserEntity benutzer;

    public TaskEntity(String titel, String inhalt, Date datum) {
        this.titel = titel;
        this.inhalt = inhalt;
        this.datum = datum;
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

    public UserEntity getBenutzer() {
        return benutzer;
    }

}
