package com.example.opendagen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Button buttonOpenDagen;
    private Button buttonInschrijven;
    private Button buttonLocatie;
    private Button buttonAgenda;
    private Button buttonSocialMedia;
    private Button buttonShare;
    private Button buttonSettings;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private int min;
    private int max;
    private boolean switchOnOff;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sp = getSharedPreferences("USER_PREF",Context.MODE_PRIVATE);
        min = sp.getInt("MIN", 18);
        max = sp.getInt("MAX", 6);
        switchOnOff = sp.getBoolean("MODESWITCH", false);

        Calendar calender = Calendar.getInstance();
        calender.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));

        if(min == 0 || max == 0) {
            if (switchOnOff) {
                setTheme(R.style.HROTheme);
            } else {
                setTheme(R.style.AppTheme);
            }
        }
        else {
            if (calender.get(calender.HOUR_OF_DAY) >= min || calender.get(calender.HOUR_OF_DAY) <= max) {
                setTheme(R.style.HROTheme);
            } else {
                setTheme(R.style.AppTheme);
            }
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nv1);
        navigationView.setNavigationItemSelectedListener(this);

        //knop om naar Open Dagen te gaan.
        buttonOpenDagen = (Button) findViewById(R.id.buttonOpenDagen);
        buttonOpenDagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOpenDagen();
            }
        });
        //knop om naar Locatie te gaan.
        buttonLocatie = (Button) findViewById(R.id.buttonLocatie);
        buttonLocatie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLocatie();
            }
        });
        //knop om naar Social Media te gaan.
        buttonSocialMedia = (Button) findViewById(R.id.buttonSocialMedia);
        buttonSocialMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSocialMedia ();
            }
        });
        //knop om naar Share te gaan.
        buttonShare = (Button) findViewById(R.id.buttonShare);
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
        //knop om naar Settings te gaan.
        buttonSettings = (Button) findViewById(R.id.buttonSettings);
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettings();
            }
        });
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