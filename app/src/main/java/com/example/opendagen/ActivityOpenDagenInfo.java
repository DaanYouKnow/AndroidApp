package com.example.opendagen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.example.opendagen.R;

import java.util.Calendar;
import java.util.TimeZone;

public class ActivityOpenDagenInfo extends AppCompatActivity {

    private int min;
    private int max;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sp = getSharedPreferences("USER_PREF", Context.MODE_PRIVATE);
        min = sp.getInt("MIN", 18);
        max = sp.getInt("MAX", 6);

        Calendar calender = Calendar.getInstance();
        calender.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));

        if(min == 0 || max == 0) {
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                setTheme(R.style.HROTheme);
            } else {
                setTheme(R.style.AppTheme);
            }
        }
        else {
            if (calender.get(calender.HOUR_OF_DAY) > min || calender.get(calender.HOUR_OF_DAY) < max /*AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM*/) {
                setTheme(R.style.HROTheme);
            } else {
                setTheme(R.style.AppTheme);
            }
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_dagen_info);
        TextView text = (TextView) findViewById(R.id.textwebsite);
        text.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
