package com.example.opendagen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import java.util.Calendar;
import java.util.TimeZone;

public class ActivityLocatie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Calendar calender = Calendar.getInstance();
        calender.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.HROTheme);
        }
        else if (calender.get(calender.HOUR_OF_DAY) > 18 || calender.get(calender.HOUR_OF_DAY) < 6/*AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM*/) {
            setTheme(R.style.HROTheme);
        }
        else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.locatieactivity);
    }
}
