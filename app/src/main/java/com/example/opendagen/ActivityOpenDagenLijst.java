package com.example.opendagen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Calendar;
import java.util.TimeZone;

public class ActivityOpenDagenLijst extends AppCompatActivity {

    ListView MyListView;
    String[] Dagen;
    String[] Info;

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
        setContentView(R.layout.opendagenlijstactivity);

        Resources res = getResources();
        MyListView =(ListView) findViewById(R.id.MyListView);
        Dagen = res.getStringArray(R.array.Dagen);
        Info = res.getStringArray(R.array.Info);

        ItemAdapter ItemAdapter = new ItemAdapter(this, Dagen, Info);
        MyListView.setAdapter(ItemAdapter);

        MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent ShowOpenDagendetail = new Intent(getApplicationContext(), ActivityOpenDagenInfo.class);
                ShowOpenDagendetail.putExtra("com.example.myapplication.ITEM_INDEX", i);
                startActivity(ShowOpenDagendetail);
            }
        });
    }
}
