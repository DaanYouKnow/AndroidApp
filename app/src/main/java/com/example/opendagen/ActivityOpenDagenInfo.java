package com.example.opendagen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatDelegate;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.opendagen.R;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class ActivityOpenDagenInfo extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

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

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nv1);
        navigationView.setNavigationItemSelectedListener(this);

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

    public void openMain() {
        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent1);
    }

    public void openOpenDagen() {
        Intent intent1 = new Intent(this, ActivityOpenDagenLijst.class);
        startActivity(intent1);
    }
    public void OpenCalender(View view){
        Intent OpenCalendarApp = getPackageManager().getLaunchIntentForPackage("com.google.android.calendar");
        startActivity(OpenCalendarApp);

    }
    public void openLocatie() {
        Intent intent4 = new Intent(this, MapsActivity.class);
        startActivity(intent4);
    }
    public void openSocialMedia() {
        Intent intent5 = new Intent(this, ActivityInfo.class);
        startActivity(intent5);
    }
    public void openSettings() {
        Intent intent7  = new Intent(this, SettingsActivity.class);
        startActivity(intent7);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case (R.id.opendays):
                Intent intent1 = new Intent(getApplicationContext(), ActivityOpenDagenLijst.class);
                startActivity(intent1);
                break;
            case (R.id.social):
                Intent intent5 = new Intent(getApplicationContext(), ActivityInfo.class);
                startActivity(intent5);
                break;
            case (R.id.locatiez):
                Intent intent4 = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent4);
                break;
            case (R.id.sharee):
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String ShareSub = "Ik ga naar hogeschool Rotterdam!\n";
                String ShareBody = "ik ga binnenkort naar opendag informatica bij HogeSchool Rotterdam! \nik heb er nu al zin in!";
                myIntent.putExtra(Intent.EXTRA_SUBJECT, ShareSub);
                myIntent.putExtra(Intent.EXTRA_TEXT, ShareBody);
                startActivity(Intent.createChooser(myIntent, "Share your excitement!"));
                break;
            case (R.id.home):
                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent2);
                break;
            case(R.id.settings):
                Intent intent7  = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent7);
                break;

        }
        return true;
    }
}
