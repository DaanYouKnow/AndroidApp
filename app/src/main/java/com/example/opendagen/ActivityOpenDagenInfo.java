package com.example.opendagen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.text.method.LinkMovementMethod;
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
        TextView text = (TextView) findViewById(R.id.textwebsite);
        text.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
