package com.example.opendagen;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.opendagen.R;
public class MainActivity extends AppCompatActivity {

    private Button buttonOpenDagen;
    private Button buttonInschrijven;
    private Button buttonLocatie;
    private Button buttonAgenda;
    private Button buttonSocialMedia;
    private Button buttonShare;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;




    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



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
                openLocatie ();
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
                openShare ();
            }
        });
    }
    public void openOpenDagen() {
        Intent intent1 = new Intent(this, Activity2.class);
        startActivity(intent1);
    }
    public void OpenCalender(View view){
        Intent OpenCalendarApp = getPackageManager().getLaunchIntentForPackage("com.google.android.calendar");
        startActivity(OpenCalendarApp);

    }
    public void openLocatie() {
        Intent intent4 = new Intent(this, Activity4.class);
        startActivity(intent4);
    }
    public void openSocialMedia() {
        Intent intent5 = new Intent(this, Activity6.class);
        startActivity(intent5);
    }
    public void openShare() {
        Intent intent6 = new Intent(this, Activity7.class);
        startActivity(intent6);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
