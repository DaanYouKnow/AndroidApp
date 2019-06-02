package com.example.opendagen;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
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

public class SettingsActivity extends AppCompatActivity {

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

        //Dark mode --> HRO mode knop 1/2
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
                    recreate();
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    recreate();
                }
            }
        });
        // einde dark mode switch 2/2

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

        //min_out1.setText(min_input.getText().toString());
        //max_out1.setText(max_input.getText().toString());
    }

    public void openMain() {
        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent1);
    }
}
