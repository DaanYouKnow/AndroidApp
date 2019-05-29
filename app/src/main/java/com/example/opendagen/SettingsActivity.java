package com.example.opendagen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Button;
import android.content.Intent;

import java.util.Calendar;
import java.util.TimeZone;

public class SettingsActivity extends AppCompatActivity {

    private Switch modeswitch;
    private Button homebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Dark mode --> HRO mode knop 1/2
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
        // einde dark mode switch 1/2

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Dark mode switch 2/2
        modeswitch=(Switch)findViewById(R.id.switch2);
        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES) {
            modeswitch.setChecked(true);
        }

        modeswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    //recreate();
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    //recreate();
                }
            }
        });
        // einde dark mode switch 2/2


        //knop om naar Open Dagen te gaan.
        homebutton = (Button) findViewById(R.id.homebutton);
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain();
            }
        });
    }

    public void openMain() {
        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent1);
    }
}
