package com.example.probono.DailylogPageFragment;

import java.util.Date;

public class DialogData {
    private String user;
    private String date;
    private String title;
    private String content;
    public DialogData(){

    }

    public DialogData(String user, String date, String title, String content) {
        this.user = user;
        this.date = date;
        this.title = title;
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
