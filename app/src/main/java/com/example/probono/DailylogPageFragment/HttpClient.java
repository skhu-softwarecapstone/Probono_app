package com.example.probono.DailylogPageFragment;

import com.example.probono.DTO.Notice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class HttpClient {
    private static final String WWW_FORM = "";
    private static  ArrayList<DialogData> dialList;
    private static HashMap<Date, DialogData> dialMap;

    private static SimpleDateFormat dataFormat;


    public static void initClient(){
        dialList = new ArrayList();
        dialList.add(new DialogData("person1", "2019-09-22", "어플리케이션 제작", "오늘은 어플리케이션을 만들었다."));
        dialList.add(new DialogData("person1", "2019-09-23", "어플리케이션 제작", "오늘은 소고기를 먹었따"));
        dialList.add(new DialogData("person1", "2019-09-25", "어플리케이션 제작", "오늘은 소고기를 먹고싶다"));
        dialList.add(new DialogData("person1", "2019-09-02", "어플리케이션 제작", "오늘은 소고기 사진을 봤다"));
        dialList.add(new DialogData("person1", "2019-09-12", "어플리케이션 제작", "소고기"));
        dialList.add(new DialogData("person1", "2019-09-03", "어플리케이션 제작", "카우"));
        dataFormat = new SimpleDateFormat("yyyy-MM-dd");
        dialMap = new HashMap<>();
        try {
            for (DialogData dialogData : dialList) {
                dialMap.put(dataFormat.parse(dialogData.getDate()), dialogData);
            }
        }catch (ParseException e){
            e.printStackTrace();

        }
    }

    public static ArrayList<DialogData> getDialog(UserInfo userInfo){


        return dialList;
    }

    public static void addDialog(DialogData data){
        Date d = null;
        try {
            d = dataFormat.parse(data.getDate());
            if (dialMap.keySet().contains(d)){
                DialogData remove = dialMap.get(d);
                dialList.remove(remove);
            }
        }catch (ParseException e){
            e.printStackTrace();
        }
        if(d != null) {
            dialMap.put(d, data);
            dialList.add(data);
        }
    }



}
