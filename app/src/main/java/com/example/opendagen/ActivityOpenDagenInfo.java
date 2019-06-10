package com.example.opendagen;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.opendagen.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ActivityOpenDagenInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.HROTheme);
        }
        else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM) {
            setTheme(R.style.HROTheme);
        }
        else setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_dagen_info);
        Button btn = (Button) findViewById(R.id.textwebsite);
        TextView Tv = (TextView) findViewById(R.id.textView);
        Tv.setText(getIntent().getStringExtra("Datum"));
        TextView Tv2 = (TextView) findViewById(R.id.textView2);
        Tv2.setText(getIntent().getStringExtra("Info"));
        TextView Tv3 = (TextView) findViewById(R.id.textView4);
        Resources res = getResources();
        Tv3.setText(res.getString(R.string.kankertext));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.hogeschoolrotterdam.nl/opleidingen/bachelor/informatica/voltijd/aanmeldprocedure-en-studiekeuzecheck/"));
                startActivity(intent);
            }
        });
        Button buttonLocatie = (Button) findViewById(R.id.buttonLocatie);
        buttonLocatie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityOpenDagenInfo.this, MapsActivity.class);
                startActivity(intent);
            }
        });
        Button buttonShare = (Button) findViewById(R.id.buttonShare);
        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String ShareSub = "Ik ga naar hogeschool Rotterdam!\n";
                String ShareBody = "ik ga binnenkort naar opendag informatica bij HogeSchool Rotterdam! \nik heb er nu al zin in!";
                myIntent.putExtra(Intent.EXTRA_SUBJECT,ShareSub);
                myIntent.putExtra(Intent.EXTRA_TEXT,ShareBody);
                startActivity(Intent.createChooser(myIntent, "Share your excitement!"));
            }
        });
        Button buttonAgenda = (Button) findViewById(R.id.buttonAgenda);
        buttonAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                Intent intent = new Intent(Intent.ACTION_EDIT);
                intent.setType("vnd.android.cursor.item/event");
                String Sday =  getIntent().getStringExtra("Datum").substring(2,4);
                String Smon =  getIntent().getStringExtra("Datum");
                Sday = Sday.replace(" ","");
                int day = Integer.parseInt(Sday);
                int mon;
                if(Smon.toLowerCase().contains("januari")){
                    mon=0;
                }
                else if(Smon.toLowerCase().contains("februari")){
                    mon=1;
                }
                else if(Smon.toLowerCase().contains("maart")){
                    mon=2;
                }
                else if(Smon.toLowerCase().contains("april")){
                    mon=3;
                }
                else if(Smon.toLowerCase().contains("mei")){
                    mon=4;
                }
                else if(Smon.toLowerCase().contains("juni")){
                    mon=5;
                }
                else if(Smon.toLowerCase().contains("juli")){
                    mon=6;
                }
                else if(Smon.toLowerCase().contains("augustus")){
                    mon=7;
                }
                else if(Smon.toLowerCase().contains("september")){
                    mon=8;
                }
                else if(Smon.toLowerCase().contains("oktober")){
                    mon=9;
                }
                else if(Smon.toLowerCase().contains("november")){
                    mon=12;
                }
                else{
                    mon=11;
                }
                int yer = Calendar.getInstance().get(Calendar.YEAR);
                if(Smon.toLowerCase().contains("2018")) {
                yer = 2018;
                }
                if(Smon.toLowerCase().contains("2019")) {
                    yer = 2019;
                }
                if(Smon.toLowerCase().contains("2020")) {
                    yer = 2020;
                }
                if(Smon.toLowerCase().contains("2021")) {
                    yer = 2021;
                }
                GregorianCalendar calDate = new GregorianCalendar(yer, mon, day);
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                        calDate.getTimeInMillis());
                intent.putExtra("allDay", true);
                intent.putExtra("title", "Hogeschool Rotterdam Opendag Informatica");
                startActivity(intent);



            }
        });
    }
}
