package com.example.opendagen;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Button buttonOpenDagen;
    private Button buttonInschrijven;
    private Button buttonLocatie;
    private Button buttonAgenda;
    private Button buttonSocialMedia;
    private Button buttonShare;
    private DrawerLayout mDrawerLayout;

    private Button buttonLogin; //LOCALK FIONDIANDKJD
    private ActionBarDrawerToggle mToggle;

    private Switch modeswitch;
    private Switch TESTSWITCH;
    public Boolean yeehaw = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Dark mode --> HRO mode knop 1/2
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.HROTheme);
        }
        else setTheme(R.style.AppTheme);
        // einde dark mode switch 1/2

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Dark mode switch 2/2
        TESTSWITCH=findViewById(R.id.TestSwitch);
        TESTSWITCH.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    yeehaw = true;
                    modeswitch.setChecked(false);
                }
            }
        });
        modeswitch=findViewById(R.id.switch2);
        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES) {
            modeswitch.setChecked(true);
        }
        modeswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(yeehaw){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    recreate();
                }
                else
                    if(isChecked){
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    recreate();
                    }

                    else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        recreate();
                    }
                }

        });

        // einde dark mode switch 2/2

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
    public void openShare() {
        Intent intent6 = new Intent(this, ActivityShare.class);
        startActivity(intent6);
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
            case(R.id.opendays):
                Intent intent1 = new Intent(getApplicationContext(), ActivityOpenDagenLijst.class);
                startActivity(intent1);
                break;
            case(R.id.social):
                Intent intent5  = new Intent(getApplicationContext(), ActivityInfo.class);
                startActivity(intent5);
                break;
            case(R.id.locatiez):
                Intent intent4  = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent4);
                break;
            case(R.id.sharee):
                Intent intent6  = new Intent(getApplicationContext(), ActivityShare.class);
                startActivity(intent6);
                break;
        }
        return true;


    }


}
