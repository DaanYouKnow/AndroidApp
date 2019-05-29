package com.example.opendagen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.ActionBarDrawerToggle;



public class ActivityInfo extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.HROTheme);
        } else setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.infoactivity);


        Button ButtonFacebook = (Button) findViewById(R.id.Facebook);
        ButtonFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Facebook = "https://www.facebook.com/hogeschoolrotterdam/";
                Uri webadress = Uri.parse(Facebook);

                Intent gotoFacebook = new Intent(Intent.ACTION_VIEW, webadress);
                if (gotoFacebook.resolveActivity(getPackageManager()) != null) {
                    startActivity(gotoFacebook);
                }
            }
        });


        Button ButtonInstagram = (Button) findViewById(R.id.Instagram);
        ButtonInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Insta = "https://www.instagram.com/hogeschoolrotterdam/";
                Uri webadress = Uri.parse(Insta);

                Intent gotoInstagram = new Intent(Intent.ACTION_VIEW, webadress);
                if (gotoInstagram.resolveActivity(getPackageManager()) != null) {
                    startActivity(gotoInstagram);
                }
            }
        });

        Button ButtonTwitter = (Button) findViewById(R.id.Twitter);
        ButtonTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Twitter = "https://twitter.com/hsrotterdam";
                Uri webadress = Uri.parse(Twitter);

                Intent gotoTwitter = new Intent(Intent.ACTION_VIEW, webadress);
                if (gotoTwitter.resolveActivity(getPackageManager()) != null) {
                    startActivity(gotoTwitter);
                }
            }
        });
        Button ButtonWebsite = (Button) findViewById(R.id.Website);
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
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nv1);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mToggle.onOptionsItemSelected(item)) {
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
            case (R.id.switch2):
                Intent intent3 = new Intent(getApplicationContext(), SwitchInDrawer.class);
                startActivity(intent3);
                break;


        }
        return true;
    }
}


