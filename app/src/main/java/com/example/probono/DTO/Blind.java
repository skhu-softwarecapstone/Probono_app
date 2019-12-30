package com.example.probono.DTO;

public class Blind {
    private int bNo;
    private String name ;
    private String content;
    private String userId;
    private String date;
    private int processState;

    public int getbNo() {
        return bNo;
    }

    public void setbNo(int bNo) {
        this.bNo = bNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getProcessState() {
        return processState;
    }

    public void setProcessState(int processState) {
        this.processState = processState;
    }
}
