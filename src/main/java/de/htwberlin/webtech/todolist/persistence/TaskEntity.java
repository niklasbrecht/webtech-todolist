package de.htwberlin.webtech.todolist.persistence;

import javax.persistence.*;

@Entity(name = "tasks")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "task_name", nullable = false)
    private String taskname;

    @Column(name = "subject")
    private String subject;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "month", nullable = false)
    private int month;

    @Column(name = "day", nullable = false)
    private int day;

    public TaskEntity(String taskname, String subject, int year, int month, int day) {
        this.taskname = taskname;
        this.subject = subject;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    protected TaskEntity(){

    }

    public long getId() {
        return id;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
