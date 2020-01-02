package com.example.probono.DailylogPageFragment;

import java.util.ArrayList;

public class MakeIntegerArray {
    public static ArrayList get(int start, int end, int interval){
        if(start>end||interval<=0)
            return null;

        ArrayList arr = new ArrayList<Integer>();
        for(int i = start; i<=end; i+=interval){
            arr.add(i);
        }


        return arr;
    }
}
