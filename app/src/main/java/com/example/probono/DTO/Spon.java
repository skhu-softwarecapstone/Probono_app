package com.example.probono.DTO;

public class Spon {

    private int id;
    private String date;
    private String title;
    private String content;
    private String volunteer_institution;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getVolunteer_institution() {
        return volunteer_institution;
    }

    public void setVolunteer_institution(String volunteer_institution) {
        this.volunteer_institution = volunteer_institution;
    }

    public String showDate(){
        String result = date.substring(3, 21);

        return result;
    }

}
