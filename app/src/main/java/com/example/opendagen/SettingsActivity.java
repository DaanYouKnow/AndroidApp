package com.example.opendagen;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

public class SettingsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Switch modeswitch;
    private Button homebutton;
    private Button apply_button;
    private Button save_button;
    private EditText min_input;
    private EditText max_input;
    private TextView min_out1;
    private TextView max_out1;
    private int min;
    private int max;

    public static final String USER_PREF = "USER_PREF";
    public static final Integer MIN = 18;
    public static final Integer MAX = 6;
    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        min = getSharedPreferences("USER_PREF", Context.MODE_PRIVATE).getInt("MIN", 18);
        max = getSharedPreferences("USER_PREF", Context.MODE_PRIVATE).getInt("MAX", 6);

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
        setContentView(R.layout.activity_settings);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nv1);
        navigationView.setNavigationItemSelectedListener(this);

        modeswitch=(Switch)findViewById(R.id.switch2);
        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES) {
            modeswitch.setChecked(true);
        }

        modeswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    recreate();
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    recreate();
                }
            }
        });

        min_out1 = (TextView) findViewById(R.id.min_out1);
        max_out1 = (TextView) findViewById(R.id.max_out1);

        min_input = (EditText) findViewById(R.id.min_input);
        max_input = (EditText) findViewById(R.id.max_input);

        sp = getSharedPreferences(USER_PREF,Context.MODE_PRIVATE);

        apply_button = (Button) findViewById(R.id.apply_button);
        save_button = (Button) findViewById(R.id.save_button);

        apply_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apply();
            }
        });

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        //knop om naar Open Dagen te gaan.
        homebutton = (Button) findViewById(R.id.homebutton);
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain();
            }
        });
    }

    public void saveData() {
        int min_output = Integer.valueOf(min_input.getText().toString());
        int max_output = Integer.valueOf(max_input.getText().toString());

        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("MIN", min_output);
        editor.putInt("MAX", max_output);
        editor.apply();

        recreate();

        Toast.makeText(this, "Opgeslagen", Toast.LENGTH_SHORT).show();
    }

    public void apply() {
        StringBuilder str = new StringBuilder();
        if (sp.contains("MIN")) {
            min_out1.setText(String.valueOf(sp.getInt("MIN", 0)));
        }
        if (sp.contains("MAX")) {
            max_out1.setText(String.valueOf(sp.getInt("MAX", 0)));
        }
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
