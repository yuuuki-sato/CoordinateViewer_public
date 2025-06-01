package com.example.coordinateviewer;

import android.widget.TextView;

public class Flag {
    public static void printFlag(TextView flag, double latitude, double longitude){
        if(latitude < 35.1582 & latitude > 35.1578){
            if(longitude < 136.9271 & longitude > 136.9263){
                flag.setText("flag{BLDG.21 is the flag!}");
            }
            else flag.setText("");
        }
        else flag.setText("");
    }

}
