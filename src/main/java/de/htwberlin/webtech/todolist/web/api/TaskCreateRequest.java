package de.htwberlin.webtech.todolist.web.api;

public class TaskCreateRequest {

    private String taskname;
    private String subject;
    private int year;
    private int month;
    private int day;

    public TaskCreateRequest(String taskname, String subject, int year, int month, int day) {
        this.taskname = taskname;
        this.subject = subject;
        this.year = year;
        this.month = month;
        this.day = day;
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
