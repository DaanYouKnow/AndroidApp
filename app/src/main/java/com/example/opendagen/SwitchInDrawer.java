package com.example.opendagen;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.widget.CompoundButton;
import android.widget.Switch;


public class SwitchInDrawer extends AppCompatActivity {
    private Switch modeswitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Dark mode --> HRO mode knop 1/2
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.HROTheme);
        } else setTheme(R.style.AppTheme);
        // einde dark mode switch 1/2

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Dark mode switch 2/2
        modeswitch = (Switch) findViewById(R.id.switch2);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            modeswitch.setChecked(true);
        }
        modeswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    recreate();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    recreate();
                }
            }
        });
    }
}

