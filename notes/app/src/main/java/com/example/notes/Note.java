package com.example.notes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Note {
    private String title;
    private String description;
    private long  ID;
    private String created_on;

    public Note(String title, String description,String created_on) {
        this.title = title;
        this.description = description;
        this.ID= Calendar.getInstance().getTimeInMillis();
        this.created_on=created_on;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }
}
