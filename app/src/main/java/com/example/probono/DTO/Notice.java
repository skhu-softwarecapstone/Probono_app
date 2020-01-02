package com.example.probono.DTO;

public class Notice {
    private String date;
    private String title;
    private String content;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    private String no;

    public Notice(String date, String title, String content) {
        this.date = date;
        this.title = title;
        this.content = content;
    }

    public Notice() {
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

    public String showDate(){
        String result = date.substring(3, 21);

        return result;
    }
}
