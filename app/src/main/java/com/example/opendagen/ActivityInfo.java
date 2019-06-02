package com.example.opendagen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;
import java.util.TimeZone;


public class ActivityInfo extends AppCompatActivity {

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
        setContentView(R.layout.infoactivity);

        Button ButtonFacebook = (Button)findViewById(R.id.Facebook);
        ButtonFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Facebook = "https://www.facebook.com/hogeschoolrotterdam/";
                Uri webadress = Uri.parse(Facebook);

                Intent gotoFacebook = new Intent(Intent.ACTION_VIEW, webadress);
                if (gotoFacebook.resolveActivity(getPackageManager()) != null){
                    startActivity(gotoFacebook);
                }
            }
        });
        Button ButtonInstagram = (Button)findViewById(R.id.Instagram);
        ButtonInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Insta = "https://www.instagram.com/hogeschoolrotterdam/";
                Uri webadress = Uri.parse(Insta);

                Intent gotoInstagram = new Intent(Intent.ACTION_VIEW, webadress);
                if (gotoInstagram.resolveActivity(getPackageManager()) != null){
                    startActivity(gotoInstagram);
                }
            }
        });

        Button ButtonTwitter = (Button)findViewById(R.id.Twitter);
        ButtonTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Twitter = "https://twitter.com/hsrotterdam";
                Uri webadress = Uri.parse(Twitter);

                Intent gotoTwitter = new Intent(Intent.ACTION_VIEW, webadress);
                if (gotoTwitter.resolveActivity(getPackageManager()) != null){
                    startActivity(gotoTwitter);
                }
            }
        });
        Button ButtonWebsite = (Button)findViewById(R.id.Website);
        ButtonWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Website = "https://www.hogeschoolrotterdam.nl/";
                Uri webadress = Uri.parse(Website);

                Intent gotoWebsite = new Intent(Intent.ACTION_VIEW, webadress);
                if (gotoWebsite.resolveActivity(getPackageManager()) != null) {
                    startActivity(gotoWebsite);
                }
            }
        });

    }
}
