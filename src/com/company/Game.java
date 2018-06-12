package com.company;

public class Game {
    private String title;
    private String type;
    private int iDNumber;
    private String dueDay;


    public Game(String title, String type, int iDNumber) {
        this.title = title;
        this.type = type;
        this.iDNumber = iDNumber;
        dueDay = "";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public int getIDNumber() {
        return iDNumber;
    }

    public String getDueDay() {
        return dueDay;
    }

    public void setDueDay(String dueDay) {
        this.dueDay = dueDay;
    }
}
