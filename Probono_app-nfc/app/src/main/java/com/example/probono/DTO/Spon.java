package com.example.probono.DTO;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Spon {

    private String title;
    private String content;
    private String address;

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return this.address;
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
