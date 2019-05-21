package com.example.opendagen;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.opendagen.R;
public class ActivityOpenDagenInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES) {
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

    }
}
