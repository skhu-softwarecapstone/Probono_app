package com.example.probono.staticValue;

public class Sv {
    public static final String serverIp = "http://58.150.133.224:8080";
    public static final String loginRequest = serverIp+"/login";


    public static final String selectSpon = serverIp+"/process/selectspon";



    public static final String noticeContent = serverIp+"/m/notice/";
    public static final String noticeCount = serverIp + "/m/noticeCount";

    public static final String sponContent = serverIp+"/m/place/";
    public static final String sponCount = serverIp + "/m/placeCount";


    public static final String blindInsert = serverIp + "/m/blind_insert/";
    public static final String blindRevise = serverIp + "/m/blind_detail/";
    public static final String blindContent = serverIp + "/m/blind/";
    public static final String blindCount = serverIp + "/m/blindCount";


    public static final String dailylogDate = serverIp + "/m/dailylog_dates";
    public static final String dailylogInsert = serverIp + "/m/dailylog_insert";
    public static final String dailylogDetail = serverIp + "/m/dailylog_detail";



    public static final String Nfc = serverIp + "/m/visitSenior_insert";
    public static final String visitDay = serverIp + "/m/visitSenior_dateSelect";


}
